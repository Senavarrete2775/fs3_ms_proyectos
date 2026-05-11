package cl.duoc.fs3.proyectos.fs3_ms_proyectos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tareas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la tarea no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "La estimación de horas es obligatoria")
    @Min(value = 1, message = "La tarea debe tomar al menos 1 hora")
    @Column(name = "estimacion_horas", nullable = false)
    private Integer estimacionHoras;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String estado; // Ej: PENDIENTE, EN_PROGRESO, COMPLETADA

    // Relación: Muchas tareas pertenecen a un Proyecto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    @JsonIgnore // Evita un bucle infinito al convertir el objeto a JSON para el BFF
    private Proyecto proyecto;
}
