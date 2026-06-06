CREATE TABLE asignaciones (
    id BIGSERIAL PRIMARY KEY,
    proyecto_id BIGINT NOT NULL,
    empleado_id BIGINT NOT NULL,
    horas_asignadas INTEGER NOT NULL,
    CONSTRAINT fk_proyecto_asignacion FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    CONSTRAINT uq_proyecto_empleado UNIQUE (proyecto_id, empleado_id)
);

-- Insertamos asignaciones iniciales que corresponden con los datos precargados en RRHH y Proyectos
-- Rodrigo Gallardo (ID 1): 20 horas en Proyecto 1 (Migración AWS)
INSERT INTO asignaciones (proyecto_id, empleado_id, horas_asignadas) VALUES (1, 1, 20);

-- Ana María Silva (ID 2): 15 horas en Proyecto 1 (Migración AWS)
INSERT INTO asignaciones (proyecto_id, empleado_id, horas_asignadas) VALUES (1, 2, 15);

-- Carlos Iturra (ID 3): 45 horas en Proyecto 1 (Migración AWS)
INSERT INTO asignaciones (proyecto_id, empleado_id, horas_asignadas) VALUES (1, 3, 45);
