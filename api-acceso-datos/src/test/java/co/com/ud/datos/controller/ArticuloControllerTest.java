package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.service.impl.ArticuloServiceImpl;
import co.com.ud.utiles.dto.ArticuloDto;
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
public class ArticuloControllerTest {

    private ArticuloController articuloController;

    @Mock
    private ArticuloServiceImpl articuloService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.articuloController = new ArticuloController(articuloService, new ModelMapper());
    }

    @Test
    public void testGetByIdeaIdEMPTY(){
        Mockito.doReturn(Optional.empty()).when(articuloService).findByIdIdea(Mockito.any());
        ResponseEntity<ArticuloDto> response = articuloController.getByIdeaId(0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetByIdeaIdSUCCESS(){
        ArticuloEntity articulo = ArticuloEntity.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .build();

        Mockito.doReturn(Optional.of(articulo)).when(articuloService).findByIdIdea(Mockito.any());
        ResponseEntity<ArticuloDto> response = articuloController.getByIdeaId(0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSave(){
        ArticuloDto responseEntity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        Mockito.doReturn(Optional.of(responseEntity)).when(articuloService).save(Mockito.any());

        ArticuloDto articulo = ArticuloDto.builder()
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        ResponseEntity<ArticuloDto> response = articuloController.save(articulo);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetByIdSUCCESS(){
        ArticuloDto responseEntity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        Mockito.doReturn(Optional.of(responseEntity)).when(articuloService).getById(1L);
        ResponseEntity<ArticuloDto> response = articuloController.getById(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetByIdEMPTY(){
        Mockito.doReturn(Optional.empty()).when(articuloService).getById(1L);
        ResponseEntity<ArticuloDto> response = articuloController.getById(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetArticulosByUserEMPTY(){
        Mockito.doReturn(new ArrayList<>()).when(articuloService).getArticulosByUser(Mockito.any());

        ResponseEntity<ArticuloDto[]> response = articuloController.getArticulosByUser(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}