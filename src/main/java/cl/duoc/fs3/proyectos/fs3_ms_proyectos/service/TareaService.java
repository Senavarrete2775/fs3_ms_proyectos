package cl.duoc.fs3.proyectos.fs3_ms_proyectos.service;


import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Proyecto;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Tarea;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.ProyectoRepository;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;

    public TareaService(TareaRepository tareaRepository, ProyectoRepository proyectoRepository) {
        this.tareaRepository = tareaRepository;
        this.proyectoRepository = proyectoRepository;
    }

    public List<Tarea> obtenerTareasPorProyecto(Long proyectoId) {
        return tareaRepository.findByProyectoId(proyectoId);
    }

    public Tarea crearTarea(Long proyectoId, Tarea tarea) {
        // Buscamos el proyecto primero
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        // Asociamos la tarea al proyecto y guardamos
        tarea.setProyecto(proyecto);
        return tareaRepository.save(tarea);
    }
}
