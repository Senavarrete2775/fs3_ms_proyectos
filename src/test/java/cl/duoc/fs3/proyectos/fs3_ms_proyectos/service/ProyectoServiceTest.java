package cl.duoc.fs3.proyectos.fs3_ms_proyectos.service;


import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Proyecto;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.model.Tarea;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.ProyectoRepository;
import cl.duoc.fs3.proyectos.fs3_ms_proyectos.repository.TareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private ProyectoService proyectoService;

    private Proyecto proyectoPrueba;

    @BeforeEach
    void setUp() {

        proyectoPrueba = Proyecto.builder()
                .id(1L)
                .nombre("Proyecto de Test")
                .estado("ACTIVO")
                .build();
    }

    @Test
    void testObtenerDetalleProyecto_DebeSumarHorasCorrectamente() {

        Tarea t1 = Tarea.builder().nombre("Tarea 1").estimacionHoras(10).build();
        Tarea t2 = Tarea.builder().nombre("Tarea 2").estimacionHoras(5).build();

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyectoPrueba));
        when(tareaRepository.findByProyectoId(1L)).thenReturn(Arrays.asList(t1, t2));


        Proyecto resultado = proyectoService.obtenerDetalleProyecto(1L);

        assertNotNull(resultado);
        assertEquals(15, resultado.getTotalHoras());
        verify(proyectoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerDetalleProyecto_DebeLanzarExcepcionSiNoExiste() {
        when(proyectoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            proyectoService.obtenerDetalleProyecto(99L);
        });
    }
}
