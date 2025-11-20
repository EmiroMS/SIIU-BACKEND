package com.siiu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siiu.dto.EditarCompromisoDTO;
import com.siiu.entity.CompromisoProyecto;
import com.siiu.service.CompromisoService;

@RestController
@RequestMapping("/compromisos")
public class CompromisoController {

    @Autowired
    private CompromisoService service;

    // CA003 - Listar compromisos del proyecto
    @GetMapping("/{proyecto}")
    public List<CompromisoProyecto> listar(@PathVariable String proyecto) {
        return service.listarPorProyecto(proyecto);
    }

    // CA004 - Editar compromiso
    @PutMapping("/{id}")
    public CompromisoProyecto editar(
            @PathVariable Long id,
            @RequestBody EditarCompromisoDTO dto) {

        return service.editarCompromiso(
                id,
                dto.getFechaEstimada(),
                dto.getNotas(),
                dto.getUsuario()
        );
    }
}
