package com.siiu.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.siiu.dto.InicioFormalDto;
import com.siiu.entity.EtapaProyecto;
import com.siiu.entity.SiiuProyecto;
import com.siiu.entity.SmapProyectoIformal;
import com.siiu.repository.EtapaProyectoRepository;
import com.siiu.repository.SiiuProyectoRepository;
import com.siiu.repository.SmapProyectoIformalRepository;

@Service
public class InicioFormalServiceImpl implements InicioFormalService {

    private final SiiuProyectoRepository proyectoRepo;
    private final SmapProyectoIformalRepository iformalRepo;
    private final EtapaProyectoRepository etapaRepo;
    private final RestTemplate restTemplate = new RestTemplate();

    public InicioFormalServiceImpl(SiiuProyectoRepository proyectoRepo,
                                   SmapProyectoIformalRepository iformalRepo,
                                   EtapaProyectoRepository etapaRepo) {
        this.proyectoRepo = proyectoRepo;
        this.iformalRepo = iformalRepo;
        this.etapaRepo = etapaRepo;
    }

    @Override
    public InicioFormalDto getFormulario(String codigoProyecto) {
        SiiuProyecto p = proyectoRepo.findById(codigoProyecto)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + codigoProyecto));

        InicioFormalDto dto = new InicioFormalDto();
        dto.setCodigoProyecto(p.getCodigo());
        dto.setDuracion(p.getDuracion());
        dto.setFechaAprobacion(p.getFechaAprobacionRechazo() != null ? p.getFechaAprobacionRechazo().atStartOfDay() : null);
        // si hay un SMAP_PROYECTO_IFORMAL previo, podríamos traerlo (opcional)
        return dto;
    }

    @Override
    @Transactional
    public InicioFormalDto guardarInicioFormal(InicioFormalDto dto) {
        // CA001: validar estado = 'APROBADO'
        SiiuProyecto proyecto = proyectoRepo.findById(dto.getCodigoProyecto())
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + dto.getCodigoProyecto()));

        if (!"APROBADO".equalsIgnoreCase(proyecto.getEstado())) {
            throw new IllegalStateException("Solo se permite iniciar formal para proyectos en estado APROBADO");
        }

        // usar la duración del proyecto si no viene en DTO
        Integer duracion = proyecto.getDuracion() != null ? proyecto.getDuracion() : dto.getDuracion();
        if (duracion == null) {
            throw new IllegalArgumentException("Duración del proyecto no disponible");
        }

        // calcular fecha final: fechaInicio + duracion meses
        LocalDateTime fechaInicio = dto.getFechaInicio();
        if (fechaInicio == null) {
            throw new IllegalArgumentException("Fecha de inicio es obligatoria");
        }
        LocalDateTime fechaFinal = fechaInicio.plusMonths(duracion);

        // Guardar en SMAP_PROYECTO_IFORMAL
        SmapProyectoIformal iformal = new SmapProyectoIformal();
        iformal.setCodigoProyecto(dto.getCodigoProyecto());
        iformal.setDuracion(duracion);
        iformal.setFechaInicio(fechaInicio);
        iformal.setFechaFinalizacion(fechaFinal);
        iformal.setUsuarioCrea(dto.getUsuarioCrea());
        iformal.setFechaCrea(LocalDateTime.now(ZoneId.systemDefault()));
        iformal.setEstado("en_edicion");
        iformal.setCodigoAprobActaCodi(dto.getCodigoAprobActaCodi());
        iformalRepo.save(iformal);

        // CA009: crear etapa única que cubra la duración total del proyecto
        // Nota: la confirmación (mensaje modal) la debe mostrar el front. Aquí creamos la etapa por defecto.
        EtapaProyecto etapa = new EtapaProyecto();
        etapa.setNombre("Etapa automática - Inicio formal");
        etapa.setDescripcion("Etapa generada automáticamente al guardar inicio formal");
        etapa.setEjecucionPresupuestal(1); // permite ejecución presupuestal
        etapa.setDuracion(duracion);
        etapa.setInicioVigencia(fechaInicio);
        etapa.setFinVigencia(fechaFinal);
        etapa.setCodigoProyecto(dto.getCodigoProyecto());
        etapa.setOrden(1);
        etapa.setConsecutivo(1);
        etapa.setUsuarioCrea(dto.getUsuarioCrea());
        etapa.setFechaCrea(LocalDateTime.now(ZoneId.systemDefault()));
        etapaRepo.save(etapa);

        dto.setFechaFinalizacion(fechaFinal);
        return dto;
    }

    @Override
    public byte[] descargarDocumentoAprobacion(String codigoProyecto) {
        // CA006: proxy al endpoint externo
        String url = "http://asone.udea.edu.co/siiu/backend/proyecto/descargar-documento/" + codigoProyecto;
        try {
            ResponseEntity<byte[]> resp = restTemplate.getForEntity(url, byte[].class);
            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                return resp.getBody();
            } else {
                throw new RestClientException("Error al descargar documento, status: " + resp.getStatusCode());
            }
        } catch (RestClientException ex) {
            throw new RuntimeException("No se pudo descargar documento de aprobación: " + ex.getMessage(), ex);
        }
    }
}