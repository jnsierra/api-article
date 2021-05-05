package co.com.ud.datos.controller;

import co.com.ud.datos.service.ParrafoService;
import co.com.ud.utiles.dto.ParrafoDto;
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
public class ParrafoControllerTest {

    private ParrafoController parrafoController;

    @Mock
    private ParrafoService parrafoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.parrafoController = new ParrafoController(parrafoService, new ModelMapper());
    }

    @Test
    public void testSaveSUCCESS(){
        ParrafoDto responseEntity = ParrafoDto.builder()
                .id(1L)
                .estado("CREADO")
                .contenido("Este es el contenido")
                .idArticulo(1L)
                .orden(1L)
                .build();

        Mockito.doReturn(Optional.of(responseEntity)).when(parrafoService).save(Mockito.any());

        ParrafoDto entity = ParrafoDto.builder()
                .estado("CREADO")
                .contenido("Este es el contenido")
                .idArticulo(1L)
                .orden(1L)
                .build();
        ResponseEntity<ParrafoDto> response = parrafoController.save(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSaveEMPTY(){
        Mockito.doReturn(Optional.empty()).when(parrafoService).save(Mockito.any());

        ParrafoDto entity = ParrafoDto.builder()
                .estado("CREADO")
                .contenido("Este es el contenido")
                .idArticulo(1L)
                .orden(1L)
                .build();
        ResponseEntity<ParrafoDto> response = parrafoController.save(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetParrafoByArticuloSUCCESS(){
        ParrafoDto entity = ParrafoDto.builder()
                .estado("CREADO")
                .contenido("Este es el contenido")
                .idArticulo(1L)
                .orden(1L)
                .build();
        ArrayList<ParrafoDto> list = new ArrayList<>();
        list.add(entity);
        Mockito.doReturn(list).when(parrafoService).getParrafoByArticulo(Mockito.any());

        ResponseEntity<ParrafoDto[]> response = parrafoController.getParrafoByArticulo(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetParrafoByArticuloEMPTY(){
        Mockito.doReturn(new ArrayList<>()).when(parrafoService).getParrafoByArticulo(Mockito.any());

        ResponseEntity<ParrafoDto[]> response = parrafoController.getParrafoByArticulo(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testModificarOrdenParrafoEMPTY(){
        Mockito.doReturn(Optional.empty()).when(parrafoService).updateOrden(Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = parrafoController.modificarOrdenParrafo(1L, 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testModificarOrdenParrafoSUCCESS(){
        Mockito.doReturn(Optional.of(Boolean.TRUE)).when(parrafoService).updateOrden(Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = parrafoController.modificarOrdenParrafo(1L, 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}