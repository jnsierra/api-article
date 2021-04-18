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

}