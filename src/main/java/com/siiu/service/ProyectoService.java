package com.siiu.service;

import com.siiu.entity.Proyecto;
import com.siiu.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository repository;

    public ProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public Optional<Proyecto> buscarPorCodigo(String codigo) {
        return repository.findById(codigo);
    }

    public Proyecto guardar(Proyecto proyecto) {
        return repository.save(proyecto);
    }

    public List<Proyecto> filtrar(
            String codigo,
            String centro,
            String estado,
            String convocatoria,
            String proceso,
            String tipo
    ) {
        return repository.filtrar(codigo, centro, estado, convocatoria, proceso, tipo);
    }
}
