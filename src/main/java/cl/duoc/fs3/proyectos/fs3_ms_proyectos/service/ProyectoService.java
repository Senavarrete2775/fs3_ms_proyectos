package cl.duoc.fs3.proyectos.fs3_ms_proyectos.service;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Proyecto;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Tarea;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.ProyectoRepository;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final TareaRepository tareaRepository;



    public ProyectoService(ProyectoRepository proyectoRepository, TareaRepository tareaRepository) {
        this.proyectoRepository = proyectoRepository;
        this.tareaRepository = tareaRepository;
    }

    public Proyecto obtenerDetalleProyecto(Long id) {

        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        List<Tarea> tareas = tareaRepository.findByProyectoId(id);

        Integer sumaHoras = tareas.stream()
                .mapToInt(Tarea::getEstimacionHoras)
                .sum();

        proyecto.setTotalHoras(sumaHoras);

        return proyecto;
    }

    public List<Proyecto> obtenerTodos() {
        return proyectoRepository.findAll();
    }

    public Proyecto crearProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto actualizarProyecto(Long id, Proyecto proyectoActualizado) {

        Proyecto proyectoExistente = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado para actualizar"));

        proyectoExistente.setNombre(proyectoActualizado.getNombre());
        proyectoExistente.setDescripcion(proyectoActualizado.getDescripcion());
        proyectoExistente.setEstado(proyectoActualizado.getEstado());
        proyectoExistente.setFechaInicio(proyectoActualizado.getFechaInicio());

        return proyectoRepository.save(proyectoExistente);
    }

    public void eliminarProyecto(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado para eliminar");
        }

        proyectoRepository.deleteById(id);
    }
}
