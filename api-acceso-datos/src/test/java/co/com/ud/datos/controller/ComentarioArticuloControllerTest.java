package co.com.ud.datos.controller;

import co.com.ud.datos.service.ComentarioArticuloService;
import co.com.ud.utiles.dto.ComentarioArticuloDto;
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

}