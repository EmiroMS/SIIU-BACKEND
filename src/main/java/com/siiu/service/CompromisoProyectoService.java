package com.siiu.service;

import com.siiu.entity.CompromisoProyecto;
import com.siiu.repository.CompromisoProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompromisoProyectoService {

    private final CompromisoProyectoRepository repository;

    public CompromisoProyectoService(CompromisoProyectoRepository repository) {
        this.repository = repository;
    }

    public List<CompromisoProyecto> listarPorProyecto(String proyecto) {
        return repository.findByProyecto(proyecto);
    }

    public Optional<CompromisoProyecto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public CompromisoProyecto editarCompromiso(Long id, CompromisoProyecto compromisoActualizado) {
        return repository.findById(id).map(c -> {
            c.setFechaEstimada(compromisoActualizado.getFechaEstimada());
            c.setComentario(compromisoActualizado.getComentario()); 
            c.setUsuarioModifica("system"); 
            c.setFechaModifica(new java.util.Date()); 
            return repository.save(c);
        }).orElseThrow(() -> new RuntimeException("Compromiso no encontrado con ID " + id));
    }
}
