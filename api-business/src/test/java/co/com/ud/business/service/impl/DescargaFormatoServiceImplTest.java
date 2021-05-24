package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.FormatoIdeaCliente;
import co.com.ud.utiles.dto.DocumentDownloadDto;
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
public class DescargaFormatoServiceImplTest {

    private DescargaFormatoServiceImpl descargaFormatoService;
    @Mock
    private FormatoIdeaCliente formatoIdeaCliente;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.descargaFormatoService = new DescargaFormatoServiceImpl("src/test/resources/document/formato_001.docx"
                , formatoIdeaCliente
                , "src/test/resources/document/");
    }

    @Test
    public void testDescargarFormatoIdea(){
        Optional<DocumentDownloadDto> down = descargaFormatoService.descargarFormatoIdea();
        Assert.assertNotNull(down);
        Assert.assertTrue(down.isPresent());
    }

    @Test
    public void testDescargarFormatoIdeaByIdIdeaEMPTY(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(formatoIdeaCliente).getFormatoByIdIdea(Mockito.any(), Mockito.any());

        Optional<DocumentDownloadDto> response = descargaFormatoService.descargarFormatoIdeaByIdIdea("sgsdfg5416", 1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }
    @Test
    public void testDescargarFormatoIdeaByIdIdeaSUCCESS(){
        FormatoIdeaDto[] list = new FormatoIdeaDto[1];
        FormatoIdeaDto item = FormatoIdeaDto.builder()
                .id(1L)
                .nombre("documento.pdf")
                .idIdea(1L)
                .base64("dgfdasgy354624r")
                .ubicacion("/opt/prueba.pdf")
                .build();
        list[0] = item;

        Mockito.doReturn(new ResponseEntity<>(list, HttpStatus.OK)).when(formatoIdeaCliente).getFormatoByIdIdea(Mockito.any(), Mockito.any());

        Optional<DocumentDownloadDto> response = descargaFormatoService.descargarFormatoIdeaByIdIdea("sgsdfg5416", 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testDescargarDocumento(){
        Optional<DocumentDownloadDto> response = descargaFormatoService.descargarDocumento("/opt/prueba.pdf");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());

    }



}