package co.com.ud.business.controller;

import co.com.ud.business.service.ArticulosService;
import co.com.ud.business.service.IdeaService;
import co.com.ud.utiles.dto.CountStateDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class EstadisticasControllerTest {

    private EstadisticasController estadisticasController;
    @Mock
    private IdeaService ideaService;
    @Mock
    private ArticulosService articulosService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.estadisticasController = new EstadisticasController(ideaService, articulosService);
    }

    @Test
    public void testObtenerIdeasByEstadoSUCCESS(){
        CountStateDto[] responseEntity = new CountStateDto[1];
        responseEntity[0] = new CountStateDto("CREADA", 1L);
        Mockito.doReturn(Optional.of(responseEntity)).when(ideaService).getIdeasNumIdeasByEstado(Mockito.any());


        ResponseEntity<CountStateDto[]> response = estadisticasController.obtenerIdeasByEstado("123456dfgerd");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testObtenerIdeasByEstadoEMPTY(){
        Mockito.doReturn(Optional.empty()).when(ideaService).getIdeasNumIdeasByEstado(Mockito.any());
        ResponseEntity<CountStateDto[]> response = estadisticasController.obtenerIdeasByEstado("123456dfgerd");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testObtenerArticuloByEstadoSUCCESS(){
        CountStateDto[] responseEntity = new CountStateDto[1];
        responseEntity[0] = new CountStateDto("POR_REVISAR", 1L);
        Mockito.doReturn(Optional.of(responseEntity)).when(articulosService).getNumArticulosByEstado(Mockito.any());

        ResponseEntity<CountStateDto[]> response = estadisticasController.obtenerArticuloByEstado("123456dfgerd");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testObtenerArticuloByEstadoEMPTY(){
        Mockito.doReturn(Optional.empty()).when(articulosService).getNumArticulosByEstado(Mockito.any());

        ResponseEntity<CountStateDto[]> response = estadisticasController.obtenerArticuloByEstado("123456dfgerd");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}