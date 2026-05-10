package cl.duoc.fs3.proyectos.fs3_ms_proyectos.controller;


import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Tarea;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/proyectos/{proyectoId}/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listarTareas(@PathVariable Long proyectoId) {
        return ResponseEntity.ok(tareaService.obtenerTareasPorProyecto(proyectoId));
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@PathVariable Long proyectoId, @Valid @RequestBody Tarea tarea) {
        return new ResponseEntity<>(tareaService.crearTarea(proyectoId, tarea), HttpStatus.CREATED);
    }
}
