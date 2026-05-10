CREATE TABLE proyectos (
                           id BIGSERIAL PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           descripcion VARCHAR(255),
                           fecha_inicio DATE NOT NULL,
                           estado VARCHAR(20) NOT NULL
);




-- Insertamos un par de datos iniciales para que puedan probar al tiro
INSERT INTO proyectos (nombre, descripcion, fecha_inicio, estado)
VALUES ('Migración AWS', 'Migración de la infraestructura legacy a la nube', '2026-05-10', 'ACTIVO');

INSERT INTO proyectos (nombre, descripcion, fecha_inicio, estado)
VALUES ('Rediseño UX/UI', 'Actualización del portal de empleados', '2026-06-01', 'PLANIFICADO');

