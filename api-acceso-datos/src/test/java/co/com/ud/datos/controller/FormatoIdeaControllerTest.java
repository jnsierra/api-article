package co.com.ud.datos.controller;

import co.com.ud.datos.entity.FormatoIdeaEntity;
import co.com.ud.datos.service.FormatoIdeaService;
import co.com.ud.utiles.dto.FormatoIdeaDto;
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
public class FormatoIdeaControllerTest {

    @Mock
    private FormatoIdeaService formatoIdeaService;

    private FormatoIdeaController formatoIdeaController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoIdeaController = new FormatoIdeaController(formatoIdeaService, new ModelMapper());
    }

    @Test
    public void testSaveSUCCESS(){
        FormatoIdeaDto request = FormatoIdeaDto
                .builder()
                .formato("APROBACION_COORDINACION")
                .nombre("FORMATO_FIRMADO")
                .ubicacion("/opt/formato_1.pdf")
                .build();

        FormatoIdeaEntity response = FormatoIdeaEntity
                .builder()
                .id(1L)
                .formato("APROBACION_COORDINACION")
                .nombre("FORMATO_FIRMADO")
                .ubicacion("/opt/formato_1.pdf")
                .build();

        Mockito.doReturn(Optional.of(response)).when(formatoIdeaService).save(Mockito.any());

        ResponseEntity<FormatoIdeaDto> rta = formatoIdeaController.save(request);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.OK, rta.getStatusCode());

    }
}