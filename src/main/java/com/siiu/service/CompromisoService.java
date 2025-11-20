package com.siiu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siiu.entity.CompromisoProyecto;
import com.siiu.repository.CompromisoRepository;

@Service
public class CompromisoService {

    @Autowired
    private CompromisoRepository repo;

    public List<CompromisoProyecto> listarPorProyecto(String proyecto) {
        return repo.findByProyecto(proyecto);
    }

    public CompromisoProyecto editarCompromiso(Long id, Date fechaEstimada, String notas, String usuario) {

        CompromisoProyecto cp = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Compromiso no encontrado"));

        cp.setFechaEstimada(fechaEstimada);
        cp.setComentario(notas);
        cp.setUsuarioModifica(usuario);
        cp.setFechaModifica(new Date());

        return repo.save(cp);
    }
}

