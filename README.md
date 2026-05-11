# 🚀 Innovatech Solutions - Microservicio de Gestión de Proyectos

Este microservicio es el componente core encargado de la administración, planificación y seguimiento de iniciativas tecnológicas dentro de Innovatech Solutions.  
Su función principal es gestionar el ciclo de vida de los proyectos y sus tareas asociadas, proporcionando cálculos automáticos de carga de trabajo para una toma de decisiones informada.

---

## 🏗️ Arquitectura de Software

El sistema implementa patrones de diseño robustos para garantizar la integridad de los datos y el desacoplamiento de componentes:

### Repository Pattern (Patrón Repositorio)
Utiliza una capa de abstracción entre la lógica de negocio y la persistencia en PostgreSQL mediante **Spring Data JPA**.  
Esto permite realizar operaciones de base de datos sin acoplamiento a SQL manual, facilitando el mantenimiento y las pruebas unitarias.

### Relación de Entidades y Cómputo Dinámico

- **One-To-Many / Many-To-One:**  
  Implementa una relación estricta entre **Proyectos** y **Tareas**, asegurando la integridad referencial y permitiendo la eliminación en cascada.

- **Lógica `@Transient`:**  
  El cálculo del `totalHoras` de un proyecto se realiza de forma dinámica mediante flujos de datos (**Streams API**), evitando la redundancia de datos en la base de datos física.

### Gestión de Migraciones con Flyway

El esquema de base de datos se gestiona mediante control de versiones (**Flyway**), asegurando que las tablas de proyectos y tareas se creen de forma idéntica en cualquier entorno.

---

## 🛠️ Stack Tecnológico

- **Backend:** Java 21 (Eclipse Temurin JRE optimizado para Alpine Linux)
- **Framework:** Spring Boot 3.x con validación de datos (Jakarta Validation)
- **Persistencia:** Hibernate en modo `validate` sobre PostgreSQL
- **Base de Datos:** PostgreSQL 15-alpine
- **Orquestación:** Docker Compose para garantizar paridad de entornos

---

## 🚀 Guía de Despliegue y Ejecución

### 📋 Prerrequisitos

- Docker Desktop / Docker Engine
- Puertos **8082** (API) y **5433** (Mapeo DB) disponibles en el host

---

### ⚡ Arranque del Entorno

Para realizar un despliegue limpio y ejecutar las migraciones iniciales, ejecute:

```bash
docker compose down -v
docker compose up -d --build
```

## 🗄️ Inicialización de Datos

Al iniciar, el sistema ejecuta automáticamente los scripts de **Flyway** para cargar datos de prueba:

- **Proyectos iniciales:** "Migración AWS" y "Rediseño UX/UI"
- **Tareas iniciales:** Configuración de VPC y migración de base de datos

---

## 🧪 Documentación del API (Puerto 8082)

### 1. Gestión de Proyectos

- **Listar todos:** `GET /api/proyectos`
- **Detalle con cálculo de horas:** `GET /api/proyectos/{id}/detalle`  
  *(Devuelve el objeto con la suma de horas de sus tareas)*
- **Crear proyecto:** `POST /api/proyectos`

### 2. Gestión de Tareas

- **Listar por proyecto:** `GET /api/proyectos/{proyectoId}/tareas`
- **Asignar nueva tarea:** `POST /api/proyectos/{proyectoId}/tareas`

---

## 🛡️ Estándares de Seguridad y Resiliencia

- **Privilegio Mínimo:** Ejecución bajo el usuario `spring` en el contenedor para mitigar riesgos de seguridad
- **Resiliencia de Persistencia:** Volumen independiente `proyectos_data` para garantizar la durabilidad de la información
- **Aislamiento de Red:** Operación aislada en `proyectos-network`, protegiendo la infraestructura de accesos externos no autorizados

---

© 2026 Innovatech Solutions - Ingeniería Civil Informática - Documentación Técnica (EV2)
