package co.com.ud.datos.controller;

import co.com.ud.datos.service.ComentarioArticuloService;
import co.com.ud.utiles.dto.ComentarioArticuloDto;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;
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
public class ComentarioArticuloControllerTest {

    private ComentarioArticuloController comentarioArticuloController;
    @Mock
    private ComentarioArticuloService comentarioArticuloService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioArticuloController = new ComentarioArticuloController(new ModelMapper(), comentarioArticuloService);
    }

    @Test
    public void testSaveEMPTY(){
        ComentarioArticuloDto entity = ComentarioArticuloDto.builder()
                .id(1L)
                .comentario("Este es un comentario")
                .build();
        Mockito.doReturn(Optional.empty()).when(comentarioArticuloService).save(Mockito.any());
        ResponseEntity<ComentarioArticuloDto> response = this.comentarioArticuloController.save(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode() );
    }

    @Test
    public void testSaveSUCCESS(){
        ComentarioArticuloDto entity = ComentarioArticuloDto.builder()
                .id(1L)
                .comentario("Este es un comentario")
                .build();

        Mockito.doReturn(Optional.of(entity)).when(comentarioArticuloService).save(Mockito.any());
        ResponseEntity<ComentarioArticuloDto> response = this.comentarioArticuloController.save(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode() );
    }

    @Test
    public void testGetComentariosByTypeAndArtEMPTY(){
        Mockito.doReturn(new ArrayList<>()).when(comentarioArticuloService).findByTypeAndArt(Mockito.any(), Mockito.any());

        ResponseEntity<ComentarioArticuloDto[]> response = comentarioArticuloController.getComentariosByTypeAndArt(1L, TYPE_COMMENTS_ARTICLE.TITULO);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetComentariosByArtIdEMPTY(){
        Mockito.doReturn(new ArrayList<>()).when(comentarioArticuloService).findByArtId(Mockito.any());

        ResponseEntity<ComentarioArticuloDto[]> response = comentarioArticuloController.getComentariosByArtId(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testUpdateResponderComentario(){
        ComentarioArticuloDto entity = ComentarioArticuloDto.builder()
                .id(1L)
                .respuestaComentario("Este es el comentario")
                .build();

        Mockito.doReturn(Optional.of(Boolean.TRUE)).when(comentarioArticuloService).updateRespuestaComentario(Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = comentarioArticuloController.updateResponderComentario(entity);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody());
    }

}