package com.siiu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.siiu.entity.EtapaProyecto;
import com.siiu.service.EtapaProyectoService;

@RestController
@RequestMapping("/api/etapas")
public class EtapaProyectoController {

    @Autowired
    private EtapaProyectoService service;

    @PostMapping("/agg")
    public EtapaProyecto crear(@RequestBody EtapaProyecto etapa) {
        return service.guardar(etapa);
    }

    @GetMapping
    public List<EtapaProyecto> listar() {
        return service.listar();
    }
}