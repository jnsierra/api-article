package co.com.ud.datos.controller;

import co.com.ud.datos.service.ComentarioFormatoArticuloService;
import co.com.ud.utiles.dto.ComentarioFormatoArticuloDto;
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
public class ComentarioFormatoArticuloControllerTest {

    private ComentarioFormatoArticuloController comentarioFormatoArticuloController;
    @Mock
    private ComentarioFormatoArticuloService comentarioFormatoArticuloService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioFormatoArticuloController = new ComentarioFormatoArticuloController(comentarioFormatoArticuloService, new ModelMapper());
    }

    @Test
    public void testSaveSUCCESS(){
        ComentarioFormatoArticuloDto entity = ComentarioFormatoArticuloDto.builder()
                .comentario("Este es un comentario")
                .build();

        ComentarioFormatoArticuloDto responseEntity = ComentarioFormatoArticuloDto.builder()
                .comentario("Este es un comentario")
                .build();

        Mockito.doReturn(Optional.of(entity)).when(comentarioFormatoArticuloService).save(Mockito.any());

        ResponseEntity<ComentarioFormatoArticuloDto> response = this.comentarioFormatoArticuloController.save(responseEntity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}