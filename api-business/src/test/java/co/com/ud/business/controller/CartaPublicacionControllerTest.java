package co.com.ud.business.controller;

import co.com.ud.business.service.CartaPublicacionService;
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

import java.util.Optional;

@SpringBootTest
public class CartaPublicacionControllerTest {

    private CartaPublicacionController cartaPublicacionController;
    @Mock
    private CartaPublicacionService cartaPublicacionService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        cartaPublicacionController = new CartaPublicacionController(cartaPublicacionService);
    }

    @Test
    public void testUploadCartaPublicacion(){
        DocumentoUploadDto responseObj = DocumentoUploadDto.builder()
                .nombre("CARTA_PUBLICACION")
                .ubicacion("/opt/")
                .extension("pdf")
                .build();
        Mockito.doReturn(Optional.of(responseObj)).when(cartaPublicacionService).persistirCarta(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<DocumentoUploadDto> response = cartaPublicacionController.uploadCartaPublicacion("agasdgkajfg8ertrgd", DocumentoUploadDto.builder().build(), 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}