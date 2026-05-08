package cl.duoc.fs3.proyectos.fs3_ms_proyectos.service;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Proyecto;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {

    private final ProyectoRepository repository;

    public ProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> obtenerTodos() {
        return repository.findAll();
    }

    public Proyecto crearProyecto(Proyecto proyecto) {
        return repository.save(proyecto);
    }

}
