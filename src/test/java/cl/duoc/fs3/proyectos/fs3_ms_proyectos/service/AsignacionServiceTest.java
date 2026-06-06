package cl.duoc.fs3.proyectos.fs3_ms_proyectos.service;

import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Asignacion;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.AsignacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsignacionServiceTest {

    @Mock
    private AsignacionRepository repository;

    @InjectMocks
    private AsignacionService service;

    private Asignacion asignacionPrueba;

    @BeforeEach
    void setUp() {
        asignacionPrueba = Asignacion.builder()
                .id(1L)
                .proyectoId(10L)
                .empleadoId(20L)
                .horasAsignadas(15)
                .build();
    }

    @Test
    void testObtenerPorProyectoId_DebeRetornarLista() {
        when(repository.findByProyectoId(10L)).thenReturn(Collections.singletonList(asignacionPrueba));

        List<Asignacion> result = service.obtenerPorProyectoId(10L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(15, result.get(0).getHorasAsignadas());
        verify(repository, times(1)).findByProyectoId(10L);
    }

    @Test
    void testObtenerPorEmpleadoId_DebeRetornarLista() {
        when(repository.findByEmpleadoId(20L)).thenReturn(Collections.singletonList(asignacionPrueba));

        List<Asignacion> result = service.obtenerPorEmpleadoId(20L);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByEmpleadoId(20L);
    }

    @Test
    void testCrearAsignacion_NuevaAsignacion_DebeGuardar() {
        when(repository.findByProyectoIdAndEmpleadoId(10L, 20L)).thenReturn(Optional.empty());
        when(repository.save(any(Asignacion.class))).thenReturn(asignacionPrueba);

        Asignacion result = service.crearAsignacion(asignacionPrueba);
        assertNotNull(result);
        verify(repository, times(1)).save(asignacionPrueba);
    }

    @Test
    void testCrearAsignacion_Existente_DebeActualizarHoras() {
        when(repository.findByProyectoIdAndEmpleadoId(10L, 20L)).thenReturn(Optional.of(asignacionPrueba));
        when(repository.save(any(Asignacion.class))).thenReturn(asignacionPrueba);

        Asignacion result = service.crearAsignacion(asignacionPrueba);
        assertNotNull(result);
        verify(repository, times(1)).save(asignacionPrueba);
    }

    @Test
    void testActualizarAsignacion_DebeModificarYGuardar() {
        when(repository.findById(1L)).thenReturn(Optional.of(asignacionPrueba));
        when(repository.save(any(Asignacion.class))).thenReturn(asignacionPrueba);

        Asignacion asignacionActualizada = Asignacion.builder().horasAsignadas(25).build();
        Asignacion result = service.actualizarAsignacion(1L, asignacionActualizada);
        assertNotNull(result);
        assertEquals(25, result.getHorasAsignadas());
        verify(repository, times(1)).save(any(Asignacion.class));
    }

    @Test
    void testActualizarAsignacion_NoExiste_DebeLanzarExcepcion() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.actualizarAsignacion(99L, asignacionPrueba);
        });
    }

    @Test
    void testEliminarAsignacion_Existe_DebeBorrar() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.eliminarAsignacion(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarAsignacion_NoExiste_DebeLanzarExcepcion() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            service.eliminarAsignacion(99L);
        });
    }

    @Test
    void testEliminarPorProyectoYEmpleado_DebeBorrar() {
        when(repository.findByProyectoIdAndEmpleadoId(10L, 20L)).thenReturn(Optional.of(asignacionPrueba));
        doNothing().when(repository).deleteById(1L);

        service.eliminarPorProyectoYEmpleado(10L, 20L);
        verify(repository, times(1)).deleteById(1L);
    }
}
