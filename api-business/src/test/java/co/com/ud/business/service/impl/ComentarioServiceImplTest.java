package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ComentarioClient;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
public class ComentarioServiceImplTest {

    private ComentarioService comentarioService;
    @Mock
    private ComentarioClient comentarioClient;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioService = new ComentarioServiceImpl(comentarioClient, "/opt/");
    }
    @Test
    public void testSaveWithFileSUCCESS()throws FileNotFoundException {
        ComentarioDto responseEntity = ComentarioDto.builder()
                .id(1L)
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .tipo_documento("pdf")
                .build();

        Mockito.doReturn(new ResponseEntity<>(responseEntity, HttpStatus.OK)).when(comentarioClient).save(Mockito.any(), Mockito.any());

        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String base64 = sc.next();
        ComentarioDto entity = ComentarioDto.builder()
                .id(1L)
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .base(base64)
                .ubicacion("/opt/1_comentario.pdf")
                .tipo_documento("pdf")
                .build();
        Optional<ComentarioDto> response = comentarioService.saveWithFile("kgfjñalkjgdfa98475928", entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}