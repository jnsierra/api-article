package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ParrafoClient;
import co.com.ud.business.service.ParrafoService;
import co.com.ud.utiles.dto.ParrafoDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ParrafoServiceImplTest {

    private ParrafoService parrafoService;
    @Mock
    private ParrafoClient parrafoClient;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        parrafoService = new ParrafoServiceImpl(parrafoClient);
    }

    @Test
    public void testObtenerParrafosByIdArtEMPTY(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(parrafoClient).getParrafoByArticulo(Mockito.any(), Mockito.any());

        Optional<List<ParrafoDto>> response = parrafoService.obtenerParrafosByIdArt("fjdañdslkjf895t7", 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testObtenerParrafosByIdArtSUCCESS(){

        ParrafoDto[] list = new ParrafoDto[1];

        ParrafoDto item = ParrafoDto.builder()
                .id(1L)
                .contenido("Este es el contenido de un parrafo")
                .build();
        list[0] = item;

        Mockito.doReturn(new ResponseEntity<>(list, HttpStatus.OK)).when(parrafoClient).getParrafoByArticulo(Mockito.any(), Mockito.any());

        Optional<List<ParrafoDto>> response = parrafoService.obtenerParrafosByIdArt("fjdañdslkjf895t7", 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}