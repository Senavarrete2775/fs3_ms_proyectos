package cl.duoc.fs3.proyectos.fs3_ms_proyectos.controller;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Proyecto;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> listarProyectos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@Valid @RequestBody Proyecto proyecto) {
        return new ResponseEntity<>(service.crearProyecto(proyecto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<Proyecto> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerDetalleProyecto(id));
    }
}
