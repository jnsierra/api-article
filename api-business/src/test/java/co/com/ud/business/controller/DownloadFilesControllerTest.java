package co.com.ud.business.controller;

import co.com.ud.business.service.DescargaFormatoService;
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
public class DownloadFilesControllerTest {

    private DownloadFilesController downloadFilesController;

    @Mock
    private DescargaFormatoService descargaFormatoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.downloadFilesController = new DownloadFilesController(descargaFormatoService);
    }

    @Test
    public void testGetFormato(){
        DocumentDownloadDto descarga = DocumentDownloadDto.builder()
                .nombre("formato")
                .document("213456ghjkl")
                .build();

        Mockito.doReturn(Optional.of(descarga)).when(descargaFormatoService).descargarFormatoIdea();

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormato();
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFormatoIdeaEMPTY(){

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormatoIdea("321654dsg", 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode() );
    }

    @Test
    public void testGetFormatoIdeaSUCCESS(){

        Mockito.doReturn(Optional.empty()).when(descargaFormatoService).descargarFormatoIdeaByIdIdea(Mockito.any(), Mockito.any());

        DocumentDownloadDto entity = DocumentDownloadDto.builder()
                .build();

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormatoIdea("321654dsg", 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode() );
    }


}