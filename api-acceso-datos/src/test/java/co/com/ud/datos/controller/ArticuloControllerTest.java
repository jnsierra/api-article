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
import java.util.List;
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

    @Test
    public void testUpdateArticuloEMPTY(){
        Mockito.doReturn(Optional.empty()).when(articuloService).updateArticulo(Mockito.any());

        ResponseEntity<ArticuloDto> response = articuloController.updateArticulo(ArticuloDto.builder().build());
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateEstadoArticuloEMPTY(){
        Mockito.doReturn(Optional.empty()).when(articuloService).updateEstadoArt(Mockito.any(), Mockito.any());

        ResponseEntity<ArticuloDto> response = articuloController.updateEstadoArticulo(1L, "ESTADO_ARTICULO");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateEstadoArticuloSUCCESS(){
        ArticuloDto entityResponse = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido")
                .build();
        Mockito.doReturn(Optional.of(entityResponse)).when(articuloService).updateEstadoArt(Mockito.any(), Mockito.any());

        ResponseEntity<ArticuloDto> response = articuloController.updateEstadoArticulo(1L, "ESTADO_ARTICULO");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetArticulosPorProfesorAndEstadoEMPTY(){

        ArticuloEntity item = ArticuloEntity.builder()
                .id(1L)
                .estado("REVISAR_PROFESOR")
                .resumen_ingles("this")
                .resumen("estado")
                .build();

        List<ArticuloEntity> lista = new ArrayList<>();
        lista.add(item);

        Mockito.doReturn(lista).when(articuloService).getArticulosByTutorAndEstado(Mockito.any(), Mockito.any());

        ResponseEntity<ArticuloDto[]> response = articuloController.getArticulosPorProfesorAndEstado(1L, "REVISAR_PROFESOR");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateUbicacionFormatoSUCCESS(){
        ArticuloEntity responseEntity = ArticuloEntity.builder().build();

        Mockito.doReturn(Optional.of(responseEntity)).when(articuloService).updateUbicacionFormato(Mockito.any(), Mockito.any());

        ResponseEntity<ArticuloDto> response = articuloController.updateUbicacionFormato(1L, "/opt/");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}