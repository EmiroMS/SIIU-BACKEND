package com.siiu.controller;

import com.siiu.entity.CompromisoProyecto;
import com.siiu.service.CompromisoProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compromisos")
public class CompromisoProyectoController {

    private final CompromisoProyectoService service;

    public CompromisoProyectoController(CompromisoProyectoService service) {
        this.service = service;
    }

    // CA003: Listar compromisos por proyecto
    @GetMapping("/{proyecto}")
    public ResponseEntity<List<CompromisoProyecto>> listar(@PathVariable String proyecto) {
        return ResponseEntity.ok(service.listarPorProyecto(proyecto));
    }

    // CA004: Editar compromiso (fecha y notas)
    @PutMapping("/{id}")
    public ResponseEntity<CompromisoProyecto> editar(@PathVariable Long id, @RequestBody CompromisoProyecto compromiso) {
        return ResponseEntity.ok(service.editarCompromiso(id, compromiso));
    }

    // CA001 y CA002 se gestionan con mensajes de ayuda (pueden exponerse en un endpoint si se requiere)
    @GetMapping("/mensajes/ayuda")
    public ResponseEntity<String> mensajeAyuda() {
        return ResponseEntity.ok("Edite el compromiso para agregar la fecha estimada de cumplimiento y las notas correspondientes.");
    }

    @GetMapping("/mensajes/info")
    public ResponseEntity<String> mensajeInfo() {
        return ResponseEntity.ok("Los siguientes compromisos fueron definidos previamente en la matrícula del proyecto, seleccione el compromiso a editar e ingrese la fecha estimada de cumplimiento.");
    }
}
