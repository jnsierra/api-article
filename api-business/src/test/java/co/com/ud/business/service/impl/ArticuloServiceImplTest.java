package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.rest.client.ComentarioArticuloClient;
import co.com.ud.business.rest.client.ControlLecturaClient;
import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.ComentarioArticuloDto;
import co.com.ud.utiles.dto.ControlLecturaDto;
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
public class ArticuloServiceImplTest {

    private ArticuloServiceImpl articuloService;
    @Mock
    private ArticuloCliente articuloCliente;
    @Mock
    private IdeaCliente ideaCliente;
    @Mock
    private ComentarioArticuloClient comentarioArticuloClient;
    @Mock
    private ControlLecturaClient controlLecturaClient;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.articuloService = new ArticuloServiceImpl(articuloCliente, ideaCliente, comentarioArticuloClient, controlLecturaClient);
    }

    @Test
    public void testSaveSUCCESSS(){
        ArticuloDto artResponseEntity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        Mockito.doReturn(new ResponseEntity<>(artResponseEntity,HttpStatus.OK)).when(articuloCliente).save(Mockito.any(), Mockito.any());
        Mockito.doReturn(new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK)).when(ideaCliente).updateStatusIdea(Mockito.any(), Mockito.any(), Mockito.any());
        ArticuloDto artEntity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();

        Optional<ArticuloDto> response = articuloService.save("1354gag", artEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testSaveEMPTY(){
        ArticuloDto artEntity = ArticuloDto.builder()
                .id(1L)
                .contenido("Este es el contenido de la idea")
                .estado("PRUEBA")
                .ideaId(1L)
                .build();
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(articuloCliente).save(Mockito.any(), Mockito.any());


        Optional<ArticuloDto> response = articuloService.save("1354gag", artEntity);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }

    @Test
    public void testRevisionArticuloSUCCESS(){
        ComentarioArticuloDto[] responseComentarios = new ComentarioArticuloDto[1];
        responseComentarios[0] = ComentarioArticuloDto.builder()
                .id(1L)
                .respuestaComentario("Esta es la respuesta")
                .build();

        Mockito.doReturn(new ResponseEntity<>(responseComentarios, HttpStatus.OK)).when(comentarioArticuloClient).getComentariosByArtId(Mockito.any(), Mockito.any());

        ControlLecturaDto[] responseControl = new ControlLecturaDto[1];
        responseControl[0] = ControlLecturaDto.builder()
                .id(1L)
                .build();

        Mockito.doReturn(new ResponseEntity<>(responseControl, HttpStatus.OK)).when(controlLecturaClient).getByArticuloId(Mockito.any(), Mockito.any());

        ArticuloDto responseUpd = ArticuloDto.builder()
                .id(1L)
                .build();

        Mockito.doReturn(new ResponseEntity<>(responseUpd,HttpStatus.OK)).when(articuloCliente).updateEstadoArticulo(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ArticuloDto> response = articuloService.revisionArticulo("sdgfjkahjg84327", 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testAprobacionArticuloSUCCESS(){
        ComentarioArticuloDto[] responseComentarios = new ComentarioArticuloDto[1];
        responseComentarios[0] = ComentarioArticuloDto.builder()
                .id(1L)
                .respuestaComentario("Esta es la respuesta")
                .build();

        Mockito.doReturn(new ResponseEntity<>(responseComentarios, HttpStatus.OK)).when(comentarioArticuloClient).getComentariosByArtId(Mockito.any(), Mockito.any());

        ArticuloDto responseUpd = ArticuloDto.builder()
                .id(1L)
                .build();

        Mockito.doReturn(new ResponseEntity<>(responseUpd,HttpStatus.OK)).when(articuloCliente).updateEstadoArticulo(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<ArticuloDto> response = articuloService.aprobacionArticulo("sdgfjkahjg84327", 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}