package com.siiu.controller;

import com.siiu.entity.Proyecto;
import com.siiu.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Proyecto>> listar() {
        return ResponseEntity.ok(service.filtrar(null, null, null, null, null, null));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtenerProyecto(@PathVariable String codigo) {
        return service.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardarProyecto(@RequestBody Proyecto proyecto) {
        try {
            Proyecto guardado = service.guardar(proyecto);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Proyecto guardado correctamente");
            response.put("data", guardado);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al guardar el proyecto");

            return ResponseEntity.status(500).body(response);
        }
    }
    
    

    // --------------------------------------------------------------
    //      /filtrar  
    // --------------------------------------------------------------
    @PostMapping("/filtrar")
    public ResponseEntity<?> filtrar(@RequestBody Map<String, Object> filtros) {

        String codigo = (String) filtros.get("codigo");
        String centro = (String) filtros.get("centroGestion");
        String estado = (String) filtros.get("estado");
        String convocatoria = (String) filtros.get("convocatoria");
        String proceso = (String) filtros.get("procesoSeleccion");
        String tipo = (String) filtros.get("subtipoProyecto");

        List<Proyecto> resultados = service.filtrar(
                codigo,
                centro,
                estado,
                convocatoria,
                proceso,
                tipo
        );

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", resultados);

        return ResponseEntity.ok(response);
    }

    
}
