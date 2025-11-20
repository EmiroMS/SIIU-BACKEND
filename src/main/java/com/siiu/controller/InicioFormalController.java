package com.siiu.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siiu.dto.InicioFormalDto;
import com.siiu.service.InicioFormalService;

@RestController
@RequestMapping("/api/inicio-formal")
public class InicioFormalController {

    private final InicioFormalService inicioFormalService;

    public InicioFormalController(InicioFormalService inicioFormalService) {
        this.inicioFormalService = inicioFormalService;
    }

    // CA002 / CA003: obtener datos para mostrar la pestaña Proceso Inicio formal
    @GetMapping("/{codigo}")
    public ResponseEntity<InicioFormalDto> obtenerFormulario(@PathVariable String codigo) {
    	System.out.println("✅ Llegó al backend con código: " + codigo);
        InicioFormalDto dto = inicioFormalService.getFormulario(codigo);
        return ResponseEntity.ok(dto);
    }

    // CA008: Guardar (persistir inicio formal y crear etapa única por defecto)
    @PostMapping
    public ResponseEntity<InicioFormalDto> guardar(@RequestBody InicioFormalDto dto) {
        InicioFormalDto saved = inicioFormalService.guardarInicioFormal(dto);
        return ResponseEntity.created(URI.create("/api/inicio-formal/" + saved.getCodigoProyecto())).body(saved);
    }

    // CA006: descargar documento de aprobación (proxy)
    @GetMapping("/{codigo}/documento-aprobacion")
    public ResponseEntity<byte[]> descargarDocumento(@PathVariable String codigo) {
        byte[] content = inicioFormalService.descargarDocumentoAprobacion(codigo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(content.length);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=aprobacion-" + codigo + ".pdf");
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}