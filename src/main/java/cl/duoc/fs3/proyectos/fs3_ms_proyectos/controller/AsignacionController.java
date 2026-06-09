package cl.duoc.fs3.proyectos.fs3_ms_proyectos.controller;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Asignacion;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.service.AsignacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos/asignaciones")
public class AsignacionController {

    private final AsignacionService service;

    public AsignacionController(AsignacionService service) {
        this.service = service;
    }

    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<Asignacion>> obtenerPorProyecto(@PathVariable Long proyectoId) {
        return ResponseEntity.ok(service.obtenerPorProyectoId(proyectoId));
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Asignacion>> obtenerPorEmpleado(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(service.obtenerPorEmpleadoId(empleadoId));
    }

    @PostMapping
    public ResponseEntity<Asignacion> crear(@RequestBody Asignacion asignacion) {
        return new ResponseEntity<>(service.crearAsignacion(asignacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignacion> actualizar(@PathVariable Long id, @RequestBody Asignacion asignacion) {
        return ResponseEntity.ok(service.actualizarAsignacion(id, asignacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarAsignacion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/proyecto/{proyectoId}/empleado/{empleadoId}")
    public ResponseEntity<Void> eliminarPorProyectoYEmpleado(@PathVariable Long proyectoId, @PathVariable Long empleadoId) {
        service.eliminarPorProyectoYEmpleado(proyectoId, empleadoId);
        return ResponseEntity.noContent().build();
    }
}
