package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.FormatoIdeaCliente;
import co.com.ud.utiles.dto.DocumentDownloadDto;
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
        this.descargaFormatoService = new DescargaFormatoServiceImpl("src/test/resources/document/formato_001.docx", formatoIdeaCliente);
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



}