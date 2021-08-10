package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.service.CartaPublicacionService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.DocumentoUploadDto;
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
public class CartaPublicacionServiceImplTest {

    @Mock
    private ArticuloCliente articuloCliente;
    private CartaPublicacionService cartaPublicacionService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.cartaPublicacionService = new CartaPublicacionServiceImpl("/opt/", articuloCliente );
    }

    @Test
    public void testPersistirCarta()throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String base64 = sc.next();

        DocumentoUploadDto documento = DocumentoUploadDto.builder()
                .extension("pdf")
                .ubicacion("/opt/CARTA_PUBLICAICON")
                .base64(base64)
                .build();

        Mockito.doReturn(new ResponseEntity<>(ArticuloDto.builder().id(1L).ubicacion_carta_publicacion("/opt/CARTA_PUBLICACION_1.pdf").build(), HttpStatus.OK ))
                .when(articuloCliente).updateUbicacionCartaPublicacion(Mockito.any(), Mockito.any());

        Optional<DocumentoUploadDto> response = this.cartaPublicacionService.persistirCarta("fgfdag34fg", 1L, documento);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}