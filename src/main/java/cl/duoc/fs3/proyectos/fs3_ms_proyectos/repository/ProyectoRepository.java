package cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository  extends JpaRepository<Proyecto,Long>{

}
