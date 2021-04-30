package co.com.ud.business.controller;

import co.com.ud.business.service.ComentarioService;
import co.com.ud.utiles.dto.ComentarioDto;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
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
public class ComentarioControllerTest {

    private ComentarioController comentarioController;
    @Mock
    private ComentarioService comentarioSerivice;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioController = new ComentarioController(comentarioSerivice);
    }

    @Test
    public void testSaveWithFileEMPTY(){
        ComentarioDto entity = ComentarioDto
                .builder()
                .comentario("ESte es un comentario")
                .base("gjkjggñlut8r")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .id_usuario(0L)
                .tipo_documento("DOC")
                .build();
        Mockito.doReturn(Optional.empty()).when(comentarioSerivice).saveWithFile(Mockito.any(),Mockito.any());

        ResponseEntity<ComentarioDto> response = comentarioController.saveWithFile("asfgfdgh3456gsdfgfdghf", entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testSaveWithFileSUCCESS(){
        ComentarioDto entity = ComentarioDto
                .builder()
                .comentario("ESte es un comentario")
                .base("gjkjggñlut8r")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .id_usuario(0L)
                .tipo_documento("DOC")
                .build();

        ComentarioDto responseEntity = ComentarioDto
                .builder()
                .id(1L)
                .comentario("ESte es un comentario")
                .base("gjkjggñlut8r")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .id_usuario(0L)
                .tipo_documento("DOC")
                .build();
        Mockito.doReturn(Optional.of(responseEntity)).when(comentarioSerivice).saveWithFile(Mockito.any(),Mockito.any());

        ResponseEntity<ComentarioDto> response = comentarioController.saveWithFile("asfgfdgh3456gsdfgfdghf", entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}