package co.com.ud.business.controller;

import co.com.ud.business.service.DescargaFormatoService;
import co.com.ud.business.service.DocumentManipulationService;
import co.com.ud.utiles.dto.DocumentDownloadDto;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class DownloadFilesControllerTest {

    private DownloadFilesController downloadFilesController;

    @Mock
    private DescargaFormatoService descargaFormatoService;
    @Mock
    private DocumentManipulationService documentManipulationService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.downloadFilesController = new DownloadFilesController(descargaFormatoService, documentManipulationService);
    }

    @Test
    public void testGetFormatoSUCCESS(){
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
    public void testGetFormatoEMPTY(){

        Mockito.doReturn(Optional.empty()).when(descargaFormatoService).descargarFormatoIdea();

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormato();
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetFormatoIdeaEMPTY(){

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormatoIdea("321654dsg", 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode() );
    }

    @Test
    public void testGetFormatoIdeaSUCCESS(){

        DocumentDownloadDto entity = DocumentDownloadDto.builder()
                .nombre("documento.pdf")
                .document("sdfdsgsadgfsd")
                .ubicacion("/opt/documento.pdf")
                .build();
        Mockito.doReturn(Optional.of(entity)).when(descargaFormatoService).descargarFormatoIdeaByIdIdea(Mockito.any(), Mockito.any());

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormatoIdea("321654dsg", 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode() );
    }

    @Test
    public void testGetDocByUrlEMPTY(){
        DocumentDownloadDto entity = DocumentDownloadDto.builder()
                .ubicacion("/opt/documento.pdf")
                .build();
        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getDocByUrl(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetDocByUrlSUCCESS(){
        DocumentDownloadDto entityDoc = DocumentDownloadDto.builder()
                .document("gfgsdfhye4rtw4")
                .nombre("documento.docx")
                .ubicacion("/repository/documentos/")
                .build();

        Mockito.doReturn(Optional.of(entityDoc)).when(descargaFormatoService).descargarDocumento(Mockito.any());

        DocumentDownloadDto entity = DocumentDownloadDto.builder()
                .ubicacion("/opt/documento.pdf")
                .build();
        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getDocByUrl(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetFormatoAlumnoEMPTY() throws IOException {

        Mockito.doReturn(Optional.of("/repository/documentos/")).when(documentManipulationService).generateFormatArt(Mockito.any(), Mockito.any());

        DocumentDownloadDto entityDoc = DocumentDownloadDto.builder()
                .document("gfgsdfhye4rtw4")
                .nombre("documento.docx")
                .ubicacion("/repository/documentos/")
                .build();
        Mockito.doReturn(Optional.of(entityDoc)).when(descargaFormatoService).descargarDocumento(Mockito.any());

        ResponseEntity<DocumentDownloadDto> response = downloadFilesController.getFormatoAlumno("dkfgjadñlkf349087", 1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}