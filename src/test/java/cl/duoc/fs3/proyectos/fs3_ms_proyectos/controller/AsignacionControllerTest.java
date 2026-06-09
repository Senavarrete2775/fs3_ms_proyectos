package cl.duoc.fs3.proyectos.fs3_ms_proyectos.controller;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Asignacion;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.service.AsignacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsignacionControllerTest {

    @Mock
    private AsignacionService service;

    @InjectMocks
    private AsignacionController controller;

    @Test
    void testObtenerPorProyecto_DebeRetornarLista() {
        Asignacion asig = Asignacion.builder().id(1L).proyectoId(10L).horasAsignadas(10).build();
        when(service.obtenerPorProyectoId(10L)).thenReturn(Collections.singletonList(asig));

        ResponseEntity<List<Asignacion>> response = controller.obtenerPorProyecto(10L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testObtenerPorEmpleado_DebeRetornarLista() {
        Asignacion asig = Asignacion.builder().id(1L).empleadoId(20L).horasAsignadas(10).build();
        when(service.obtenerPorEmpleadoId(20L)).thenReturn(Collections.singletonList(asig));

        ResponseEntity<List<Asignacion>> response = controller.obtenerPorEmpleado(20L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testCrear_DebeRetornar201() {
        Asignacion asig = Asignacion.builder().proyectoId(10L).empleadoId(20L).horasAsignadas(10).build();
        when(service.crearAsignacion(any(Asignacion.class))).thenReturn(asig);

        ResponseEntity<Asignacion> response = controller.crear(asig);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void testActualizar_DebeRetornar200() {
        Asignacion asig = Asignacion.builder().id(1L).horasAsignadas(10).build();
        when(service.actualizarAsignacion(eq(1L), any(Asignacion.class))).thenReturn(asig);

        ResponseEntity<Asignacion> response = controller.actualizar(1L, asig);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testEliminar_DebeRetornar204() {
        doNothing().when(service).eliminarAsignacion(1L);

        ResponseEntity<Void> response = controller.eliminar(1L);
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void testEliminarPorProyectoYEmpleado_DebeRetornar204() {
        doNothing().when(service).eliminarPorProyectoYEmpleado(10L, 20L);

        ResponseEntity<Void> response = controller.eliminarPorProyectoYEmpleado(10L, 20L);
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
    }
}
