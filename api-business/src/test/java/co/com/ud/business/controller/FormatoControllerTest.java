package co.com.ud.business.controller;

import co.com.ud.business.service.FormatoService;
import co.com.ud.utiles.dto.FormatoDto;
import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
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
public class FormatoControllerTest {

    private FormatoController formatoController;
    @Mock
    private FormatoService formatoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoController = new FormatoController(formatoService);
    }

    @Test
    public void testSaveEMPTY(){
        FormatoDto entity = FormatoDto.builder()
                .id(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .build();
        Mockito.doReturn(Optional.empty()).when(formatoService).guardarFormatoArt(Mockito.any(),Mockito.any());

        ResponseEntity<FormatoDto> response = formatoController.save("jgñadlskgjfvasdg9087098ag", entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    public void testSaveSUCEESS(){
        FormatoDto entity = FormatoDto.builder()
                .id(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .build();
        Mockito.doReturn(Optional.of(entity)).when(formatoService).guardarFormatoArt(Mockito.any(),Mockito.any());

        ResponseEntity<FormatoDto> response = formatoController.save("jgñadlskgjfvasdg9087098ag", entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}