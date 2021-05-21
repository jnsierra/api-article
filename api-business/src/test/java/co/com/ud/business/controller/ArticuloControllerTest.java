package co.com.ud.business.controller;

import co.com.ud.business.service.ArticulosService;
import co.com.ud.utiles.dto.ArticuloDto;
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
public class ArticuloControllerTest {

    private ArticuloController articuloController;
    @Mock
    private ArticulosService articulosService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.articuloController = new ArticuloController(articulosService);
    }

    @Test
    public void testSave(){
        ArticuloDto responseEntity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        Mockito.doReturn(Optional.of(responseEntity)).when(articulosService).save(Mockito.any(), Mockito.any());


        ArticuloDto articulo = ArticuloDto.builder()
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        ResponseEntity<ArticuloDto> response = this.articuloController.save("asdfadgag", articulo);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEnviaArticuloRevisionEMPTY(){
        ResponseEntity<ArticuloDto> response = articuloController.enviaArticuloRevision("1231564s",1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    public void testEnviaArticuloRevisionSUCCESS(){
        ArticuloDto entity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido")
                .conclusion("Esta es la conclusion")
                .build();

        Mockito.doReturn(Optional.of(entity)).when(articulosService).revisionArticulo(Mockito.any(), Mockito.any());

        ResponseEntity<ArticuloDto> response = articuloController.enviaArticuloRevision("1231564s",1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}