# 🚀 Innovatech Solutions - Microservicio de Gestión de Proyectos

Este microservicio es el componente del core encargado de la administración, planificación y seguimiento de proyectos, tareas y asignación de carga de trabajo de los empleados en Innovatech.

Su responsabilidad principal es mantener el ciclo de vida de los proyectos, gestionar el catálogo de tareas por proyecto, y registrar qué empleados están asignados a qué proyectos y cuántas horas semanales dedican a cada uno.

---

## 🏗️ Arquitectura de Software

El microservicio está diseñado bajo principios de desacoplamiento y consistencia:

*   **Repository Pattern (Patrón Repositorio):** Abstracción de persistencia de datos mediante **Spring Data JPA** e **Hibernate** sobre **PostgreSQL**.
*   **Cómputo Dinámico de Horas (`@Transient`):** El cálculo de `totalHoras` acumulado por proyecto se realiza en tiempo de ejecución (mediante streams de Java) sumando las horas de todas sus tareas, garantizando consistencia y evitando duplicación en tablas.
*   **Upsert en Asignaciones:** La lógica de negocio del servicio de asignaciones detecta si un empleado ya está asignado a un proyecto. En caso afirmativo, actualiza las horas acumuladas en lugar de crear un registro duplicado.
*   **Control de Versiones de Base de Datos (Flyway):** Todas las migraciones del esquema se ejecutan al iniciar, cargando datos de prueba iniciales (proyectos: "Migración AWS", "Rediseño UX/UI", y sus respectivas tareas/asignaciones).

---

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Java 21 (Eclipse Temurin JRE)
*   **Framework:** Spring Boot 3.x (Jakarta Validation para DTOs)
*   **Base de Datos:** PostgreSQL 15-alpine (Administrada por Flyway)
*   **Persistencia:** JPA / Hibernate (Modo `validate` en producción/docker)
*   **Orquestación:** Docker Compose

---

## 🚀 Guía de Despliegue y Ejecución

### 📋 Prerrequisitos

*   Docker Desktop / Docker Engine
*   Para ejecutar localmente (fuera de Docker):
    *   PostgreSQL corriendo en el puerto **5432** con base de datos `db_proyectos`, usuario `postgres` y contraseña `admin123`.

### 🐳 Ejecución con Docker

El microservicio incluye su propio `docker-compose.yml` que levanta la base de datos PostgreSQL aislada y la aplicación API.

1. En la raíz del directorio `fs3_ms_proyectos`, ejecute:
   ```bash
   # Limpiar volúmenes y reiniciar
   docker compose down -v
   
   # Levantar la base de datos y la API
   docker compose up -d --build
   ```
2. **Puertos expuestos:**
   *   **API:** Puerto **`8082`** en el host.
   *   **Base de Datos (PostgreSQL):** Puerto **`5433`** en el host (mapeado internamente a 5432 en el contenedor para evitar conflictos).

### 💻 Ejecución Local (Desarrollo)

Para ejecutar el servicio localmente sin Docker:
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```
*Nota: Si ejecuta localmente fuera de Docker, asegúrese de ajustar las propiedades de conexión de la base de datos en `src/main/resources/application.properties` (apuntando a `localhost:5432` en lugar de `innovatech-db-proyectos:5432`).*

---

## 🧪 Ejecución de Pruebas Unitarias

El proyecto está configurado con pruebas unitarias que validan la lógica de negocio de proyectos, tareas y asignaciones. La cobertura de Jacoco está en el rango de **60% a 75%**.

Para ejecutar los tests, el entorno utiliza una base de datos en memoria **H2**, por lo que **no** requiere una base de datos física levantada:

```bash
# En Windows (CMD o PowerShell)
.\mvnw.cmd test

# En Linux o macOS
./mvnw test
```

Para generar el reporte de cobertura de Jacoco:
```bash
# Windows
.\mvnw.cmd clean verify

# Linux / macOS
./mvnw clean verify
```
El reporte se generará en: `target/site/jacoco/index.html`.

---

## 🔌 Documentación del API (Puerto 8082)

### 1. Proyectos (`/api/proyectos`)
| Método | Endpoint | Payload (Request Body) | Descripción | Código de Éxito |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/api/proyectos` | Ninguno | Obtiene todos los proyectos básicos. | `200 OK` |
| `GET` | `/api/proyectos/{id}/detalle` | Ninguno | Obtiene el proyecto incluyendo la suma de horas de sus tareas. | `200 OK` |
| `POST` | `/api/proyectos` | `Proyecto` (JSON) | Registra un nuevo proyecto. | `201 Created` |
| `PUT` | `/api/proyectos/{id}` | `Proyecto` (JSON) | Actualiza datos de un proyecto. | `200 OK` |
| `DELETE` | `/api/proyectos/{id}` | Ninguno | Elimina un proyecto (eliminación en cascada). | `204 No Content` |

### 2. Tareas (`/api/proyectos/{proyectoId}/tareas`)
| Método | Endpoint | Payload (Request Body) | Descripción | Código de Éxito |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/api/proyectos/{proyectoId}/tareas` | Ninguno | Lista todas las tareas asociadas a un proyecto. | `200 OK` |
| `POST` | `/api/proyectos/{proyectoId}/tareas` | `Tarea` (JSON) | Registra una nueva tarea dentro de un proyecto. | `201 Created` |

### 3. Asignaciones (`/api/proyectos/asignaciones`)
| Método | Endpoint | Payload (Request Body) | Descripción | Código de Éxito |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/api/proyectos/asignaciones/proyecto/{proyectoId}` | Ninguno | Obtiene todas las asignaciones asociadas a un proyecto. | `200 OK` |
| `GET` | `/api/proyectos/asignaciones/empleado/{empleadoId}` | Ninguno | Obtiene todas las asignaciones asignadas a un empleado. | `200 OK` |
| `POST` | `/api/proyectos/asignaciones` | `Asignacion` (JSON) | Crea una asignación. Si ya existe, actualiza las horas (Upsert). | `201 Created` |
| `PUT` | `/api/proyectos/asignaciones/{id}` | `Asignacion` (JSON) | Modifica las horas de una asignación por ID. | `200 OK` |
| `DELETE` | `/api/proyectos/asignaciones/{id}` | Ninguno | Elimina una asignación por ID. | `204 No Content` |
| `DELETE` | `/api/proyectos/asignaciones/proyecto/{proyectoId}/empleado/{empleadoId}` | Ninguno | Elimina la asignación de un empleado en un proyecto. | `204 No Content` |

---

© 2026 Innovatech Solutions - Ingeniería Civil Informática - Documentación Técnica (EV2)
