package cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    List<Asignacion> findByProyectoId(Long proyectoId);
    List<Asignacion> findByEmpleadoId(Long empleadoId);
    Optional<Asignacion> findByProyectoIdAndEmpleadoId(Long proyectoId, Long empleadoId);
}
