package cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
    List<Tarea> findByProyectoId(Long proyectoId);
}
