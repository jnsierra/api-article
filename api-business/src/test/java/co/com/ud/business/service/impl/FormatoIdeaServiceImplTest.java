package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.FormatoIdeaCliente;
import co.com.ud.business.service.FormatoIdeaService;
import co.com.ud.business.service.IdeaService;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
public class FormatoIdeaServiceImplTest {

    private FormatoIdeaService formatoIdeaService;
    @Mock
    private FormatoIdeaCliente formatoIdeaCliente;
    @Mock
    private IdeaService ideaService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoIdeaService = new FormatoIdeaServiceImpl(formatoIdeaCliente, "/opt/", ideaService);
    }

    @Test
    public void testSaveFormato(){
        FormatoIdeaDto formatoIdeaDto = FormatoIdeaDto
                .builder().build();

        FormatoIdeaDto responseDto = FormatoIdeaDto
                .builder()
                .id(1L)
                .formato("FORMATO_FIRMADO")
                .idIdea(1l)
                .nombre("FORMATO")
                .ubicacion("/repository/documents/")
                .build();
        Mockito.doReturn(new ResponseEntity<>(responseDto, HttpStatus.OK)).when(formatoIdeaCliente).save(Mockito.any(), Mockito.any());

        Optional<FormatoIdeaDto> response = formatoIdeaService.saveFormato("123gfd", formatoIdeaDto);
        Assert.assertNotNull(response);
        Assert.assertEquals(Boolean.TRUE, response.isPresent());
    }

    @Test
    public void testPersistirFormatoIdeaSUCCESS_FIRST_VERSION()throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String base64 = sc.next();


        FormatoIdeaDto responseDto = FormatoIdeaDto
                .builder()
                .id(1L)
                .formato("PROCESO_AUT_TUTOR")
                .tipo("docx")
                .idIdea(1l)
                .nombre("FORMATO")
                .base64(base64)
                .build();

        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(formatoIdeaCliente).getFormatoByIdIdea(Mockito.any(),Mockito.any());

        Mockito.doReturn(new ResponseEntity<>(responseDto, HttpStatus.OK)).when(formatoIdeaCliente).save(Mockito.any(), Mockito.any());

        Optional<FormatoIdeaDto> response = formatoIdeaService.persistirFormatoIdea("1321546fadf",responseDto);
        Assert.assertNotNull(response);
        Assert.assertEquals(Boolean.TRUE, response.isPresent());
    }

    @Test
    public void testPersistirFormatoIdeaSUCCESS_SECOND_VERSION()throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("base64Text.txt").getFile());
        Scanner sc = new Scanner(file);
        String base64 = sc.next();

        FormatoIdeaDto[] list = new FormatoIdeaDto[1];
        FormatoIdeaDto responseEntity = FormatoIdeaDto
                .builder()
                .id(1L)
                .formato("PROCESO_AUT_TUTOR")
                .tipo("docx")
                .idIdea(1l)
                .nombre("FORMATO")
                .base64(base64)
                .build();
        list[0] = responseEntity;



        Mockito.doReturn(new ResponseEntity<>(list,HttpStatus.OK)).when(formatoIdeaCliente).getFormatoByIdIdea(Mockito.any(),Mockito.any());



        Mockito.doReturn(new ResponseEntity<>(responseEntity, HttpStatus.OK)).when(formatoIdeaCliente).save(Mockito.any(), Mockito.any());

        FormatoIdeaDto entity = FormatoIdeaDto
                .builder()
                .formato("PROCESO_AUT_TUTOR")
                .tipo("docx")
                .idIdea(1l)
                .nombre("FORMATO")
                .base64(base64)
                .build();
        Optional<FormatoIdeaDto> response = formatoIdeaService.persistirFormatoIdea("1321546fadf",entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(Boolean.TRUE, response.isPresent());
    }

}