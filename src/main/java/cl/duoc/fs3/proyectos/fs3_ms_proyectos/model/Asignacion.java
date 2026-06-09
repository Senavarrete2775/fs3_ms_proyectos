package cl.duoc.fs3.proyectos.fs3_ms_proyectos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asignaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del proyecto es obligatorio")
    @Column(name = "proyecto_id", nullable = false)
    private Long proyectoId;

    @NotNull(message = "El ID del empleado es obligatorio")
    @Column(name = "empleado_id", nullable = false)
    private Long empleadoId;

    @NotNull(message = "Las horas asignadas son obligatorias")
    @Column(name = "horas_asignadas", nullable = false)
    private Integer horasAsignadas;
}
