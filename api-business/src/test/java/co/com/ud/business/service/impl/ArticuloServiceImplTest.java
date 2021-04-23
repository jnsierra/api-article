package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.utiles.dto.ArticuloDto;
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

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.articuloService = new ArticuloServiceImpl(articuloCliente, ideaCliente);
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

}