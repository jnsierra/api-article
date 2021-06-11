package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.rest.client.FormatoClient;
import co.com.ud.business.service.FormatoService;
import co.com.ud.utiles.dto.ArticuloDto;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
public class FormatoServiceImplTest {

    private FormatoService formatoService;
    @Mock
    private FormatoClient formatoClient;
    @Mock
    private ArticuloCliente articuloCliente;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoService = new FormatoServiceImpl(formatoClient, "/opt/", articuloCliente);
    }

    @Test
    public void testGuardarFormatoArt()throws FileNotFoundException {


        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String base64 = sc.next();

        FormatoDto entity = FormatoDto.builder()
                .idArticulo(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .nombre("docx")
                .base64(base64)
                .build();

        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(formatoClient).getFormatoByIdArt(Mockito.any(), Mockito.any());

        ArticuloDto articulo = ArticuloDto
                .builder()
                .id(1L)
                .build();
        Mockito.doReturn(new ResponseEntity<>(articulo, HttpStatus.OK)).when(articuloCliente).updateEstadoArticulo(Mockito.any(), Mockito.any(), Mockito.any());

        FormatoDto formatoEntity = FormatoDto.builder()
                .id(1L)
                .base64("dsfdsafda4r")
                .idArticulo(1L)
                .build();
        Mockito.doReturn(new ResponseEntity<>(formatoEntity, HttpStatus.OK)).when(formatoClient).save(Mockito.any(), Mockito.any());

        Optional<FormatoDto> response = formatoService.guardarFormatoArt("kfsdcnjgñsldfkgj543096754", entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testGuardarFormatoArtWITHOUT_NAME()throws FileNotFoundException {
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)).when(formatoClient).getFormatoByIdArt(Mockito.any(), Mockito.any());

        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String base64 = sc.next();

        FormatoDto entity = FormatoDto.builder()
                .idArticulo(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .nombre("docx")
                .base64(base64)
                .build();

        Optional<FormatoDto> response = formatoService.guardarFormatoArt("kfsdcnjgñsldfkgj543096754", entity);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }

}