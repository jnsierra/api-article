package co.com.ud.business.controller;

import co.com.ud.business.service.FormatoIdeaService;
import co.com.ud.utiles.dto.FormatoIdeaDto;
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
public class FormatoIdeaControllerTest {

    private FormatoIdeaController formatoIdeaController;
    @Mock
    private FormatoIdeaService formatoIdeaService;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoIdeaController = new FormatoIdeaController(formatoIdeaService);
    }

    @Test
    public void testSaveSUCCESS(){
        FormatoIdeaDto formatoIdeaDto = FormatoIdeaDto
                .builder()
                .formato("FORMATO_FIRMADO")
                .idIdea(1l)
                .nombre("FORMATO")
                .build();
        FormatoIdeaDto responseDto = FormatoIdeaDto
                .builder()
                .id(1L)
                .formato("FORMATO_FIRMADO")
                .idIdea(1l)
                .nombre("FORMATO")
                .build();
        Mockito.doReturn(Optional.of(responseDto)).when(formatoIdeaService).persistirFormatoIdea(Mockito.any(), Mockito.any());
        ResponseEntity<FormatoIdeaDto> rta = formatoIdeaController.save("13456gfda", formatoIdeaDto);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.CREATED, rta.getStatusCode());
    }

}