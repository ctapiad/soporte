package com.fullstack.soporte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.fullstack.soporte.model.Soporte;
import com.fullstack.soporte.model.dto.UsuarioDto;
import com.fullstack.soporte.model.entity.SoporteEntity;
import com.fullstack.soporte.repository.SoporteRepository;
import com.fullstack.soporte.service.SoporteService;

public class SoporteTest {

    @Mock
    private SoporteRepository soporteRepository;
    
    @InjectMocks
    private SoporteService soporteService;

    private Soporte soporte;
    private SoporteEntity soporteEntity;


    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        soporte = new Soporte( 1, "Cristian", "Esto es una prueba", "Probando", "11.222.333-4", new java.util.Date());
        soporteEntity = new SoporteEntity();
        soporteEntity.setId(1);
        soporteEntity.setNombre("Cristian");
        soporteEntity.setDetalle("Esto es una prueba");
        soporteEntity.setEstado("Probando");
        soporteEntity.setUser_rut("11.222.333-4");
        soporteEntity.setFecha_creacion(new java.util.Date());

        UsuarioDto usuarioMock = new UsuarioDto("11.222.333-4", "Cristian", "Apellido", "correo@correo.com", 123456789);
        when(restTemplate.getForObject(anyString(), eq(UsuarioDto.class))).thenReturn(usuarioMock);
    }

    @Test
    public void testCrearSoporte() {
    when(soporteRepository.existsById(soporte.getId())).thenReturn(false);
    when(soporteRepository.save(any(SoporteEntity.class))).thenReturn(soporteEntity);

    String result = soporteService.crearSoporte(soporte);
    assertEquals("Soporte creado con exito", result);
    }

    @Test
    public void testTraerSoporteporId() {
    when(soporteRepository.findById(soporte.getId())).thenReturn(Optional.of(soporteEntity));

    Soporte result = soporteService.obtenerSoporte(soporte.getId());

    assertEquals(soporte.getId(), result.getId());
    assertEquals(soporte.getNombre(), result.getNombre());
}

    @Test
    public void testTraerSoporteNoExiste() {
        when(soporteRepository.findById(soporte.getId())).thenReturn(null);

        Soporte result = soporteService.obtenerSoporte(soporte.getId());

        assertEquals(null, result);
    }

    @Test
    public void testActualizarSoporte_existe() {
        when(soporteRepository.findById(soporte.getId())).thenReturn(Optional.of(soporteEntity));
        when(soporteRepository.existsById(soporte.getId())).thenReturn(true);
        when(soporteRepository.save(any(SoporteEntity.class))).thenReturn(soporteEntity);

        String result = soporteService.modificarSoporte(soporte);

        assertEquals("", result);
    }

    @Test
    public void testBorrarSoporte() {
        when(soporteRepository.existsById(1)).thenReturn(true);
        doNothing().when(soporteRepository).deleteById(1);

        String result = soporteService.borrarSoporte(1);

        assertEquals("Soporte eliminado con éxito", result);
    }


}
