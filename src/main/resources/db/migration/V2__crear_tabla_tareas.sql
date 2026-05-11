CREATE TABLE tareas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    estimacion_horas INTEGER NOT NULL,
    estado VARCHAR(20) NOT NULL,
    proyecto_id BIGINT NOT NULL,
    CONSTRAINT fk_proyecto FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE
);

-- Insertamos tareas de prueba asociadas al proyecto 1 (Migración AWS) que creamos en el V1
INSERT INTO tareas (nombre, estimacion_horas, estado, proyecto_id)
VALUES ('Configurar VPC en AWS', 8, 'EN_PROGRESO', 1);

INSERT INTO tareas (nombre, estimacion_horas, estado, proyecto_id)
VALUES ('Migrar base de datos PostgreSQL', 16, 'PENDIENTE', 1);