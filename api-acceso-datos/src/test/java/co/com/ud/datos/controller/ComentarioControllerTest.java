package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.datos.service.ComentarioService;
import co.com.ud.utiles.dto.ComentarioDto;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
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
public class ComentarioControllerTest {

    private ComentarioController comentarioController;
    @Mock
    private ComentarioService comentarioService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioController = new ComentarioController(comentarioService, new ModelMapper());
    }

    @Test
    public void testSaveSUCCESS(){
        ComentarioEntity response = ComentarioEntity.builder()
                .id(1L)
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .build();
        Mockito.doReturn(Optional.of(response)).when(comentarioService).save(Mockito.any());

        ComentarioDto entity = ComentarioDto.builder()
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .build();

        ResponseEntity<ComentarioDto> rta = comentarioController.save(entity);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.OK, rta.getStatusCode());
    }

    @Test
    public void testSaveFAILED(){
        Mockito.doReturn(Optional.empty()).when(comentarioService).save(Mockito.any());

        ComentarioDto entity = ComentarioDto.builder()
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .build();

        ResponseEntity<ComentarioDto> rta = comentarioController.save(entity);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.NO_CONTENT, rta.getStatusCode());
    }

    @Test
    public void testGetComentariosByLLaveAndTypeComments(){
        List<ComentarioEntity> list = new ArrayList<>();
        ComentarioEntity comentario = ComentarioEntity
                .builder()
                .id(1L)
                .llave(1L)
                .id_usuario(1L)
                .comentario("Pruebas")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_IDEA)
                .build();
        list.add(comentario);


        Mockito.doReturn(list).when(comentarioService).findByLlaveAndTipoComentario(Mockito.any(), Mockito.any());

        ResponseEntity<ComentarioDto[]> comentarioRta = comentarioController.getComentariosByLLaveAndTypeComments(1L, TYPE_COMMENTS.RECHAZO_FORMATO_IDEA);
        Assert.assertNotNull(comentarioRta);
        Assert.assertEquals(HttpStatus.OK, comentarioRta.getStatusCode());
    }

    @Test
    public void testUpdateUbicacionComentarioSUCCESS(){
        ComentarioDto comentario = ComentarioDto.builder()
                .id(1L)
                .ubicacion("/opt/documentos/01_documento.pdf")
                .build();
        Mockito.doReturn(Optional.of(Boolean.TRUE)).when(comentarioService).updateComentarioUbicacion(Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = comentarioController.updateUbicacionComentario(comentario);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateUbicacionComentarioEMPTY(){
        ComentarioDto comentario = ComentarioDto.builder()
                .id(1L)
                .ubicacion("/opt/documentos/01_documento.pdf")
                .build();
        Mockito.doReturn(Optional.empty()).when(comentarioService).updateComentarioUbicacion(Mockito.any(), Mockito.any());
        ResponseEntity<Boolean> response = comentarioController.updateUbicacionComentario(comentario );
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}