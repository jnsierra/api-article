package co.com.ud.datos.controller;

import co.com.ud.datos.service.ControlLecturaService;
import co.com.ud.utiles.dto.ControlLecturaDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class ControlLecturaControllerTest {

    public ControlLecturaController controlLecturaController;
    @Mock
    public ControlLecturaService controlLecturaService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.controlLecturaController = new ControlLecturaController(controlLecturaService, new ModelMapper());
    }

    @Test
    public void testSaveSUCCESS(){
        ControlLecturaDto responseEnity = ControlLecturaDto.builder()
                .id(1L)
                .estado("CREADO")
                .autor("Nicolas")
                .comentario("Este es una comentario")
                .link("http://example.com")
                .orden(1L)
                .year(2020L)
                .build();

        Mockito.doReturn(Optional.of(responseEnity)).when(controlLecturaService).save(Mockito.any());

        ControlLecturaDto entity = ControlLecturaDto.builder()
                .estado("CREADO")
                .autor("Nicolas")
                .comentario("Este es una comentario")
                .link("http://example.com")
                .orden(1L)
                .year(2020L)
                .build();

        ResponseEntity<ControlLecturaDto> response = controlLecturaController.save(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveEMPTY(){


        Mockito.doReturn(Optional.empty()).when(controlLecturaService).save(Mockito.any());

        ControlLecturaDto entity = ControlLecturaDto.builder()
                .estado("CREADO")
                .autor("Nicolas")
                .comentario("Este es una comentario")
                .link("http://example.com")
                .orden(1L)
                .year(2020L)
                .build();

        ResponseEntity<ControlLecturaDto> response = controlLecturaController.save(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetByArticuloIdEMPTY(){
        Mockito.doReturn(new ArrayList<>()).when(controlLecturaService).getByIdArticulo(Mockito.any());

        ResponseEntity<ControlLecturaDto[]> response = controlLecturaController.getByArticuloId(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }



}