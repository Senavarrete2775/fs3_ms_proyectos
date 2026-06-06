package cl.duoc.fs3.proyectos.fs3_ms_proyectos.service;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Asignacion;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.AsignacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignacionService {

    private final AsignacionRepository repository;

    public AsignacionService(AsignacionRepository repository) {
        this.repository = repository;
    }

    public List<Asignacion> obtenerPorProyectoId(Long proyectoId) {
        return repository.findByProyectoId(proyectoId);
    }

    public List<Asignacion> obtenerPorEmpleadoId(Long empleadoId) {
        return repository.findByEmpleadoId(empleadoId);
    }

    public Asignacion crearAsignacion(Asignacion asignacion) {
        Optional<Asignacion> existente = repository.findByProyectoIdAndEmpleadoId(
                asignacion.getProyectoId(), asignacion.getEmpleadoId());
        
        if (existente.isPresent()) {
            Asignacion asig = existente.get();
            asig.setHorasAsignadas(asignacion.getHorasAsignadas());
            return repository.save(asig);
        }
        return repository.save(asignacion);
    }

    public Asignacion actualizarAsignacion(Long id, Asignacion asignacionActualizada) {
        Asignacion existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID: " + id));
        
        existente.setHorasAsignadas(asignacionActualizada.getHorasAsignadas());
        return repository.save(existente);
    }

    public void eliminarAsignacion(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Asignación no encontrada con ID: " + id);
        }
        repository.deleteById(id);
    }

    public void eliminarPorProyectoYEmpleado(Long proyectoId, Long empleadoId) {
        Optional<Asignacion> existente = repository.findByProyectoIdAndEmpleadoId(proyectoId, empleadoId);
        existente.ifPresent(asignacion -> repository.deleteById(asignacion.getId()));
    }
}
