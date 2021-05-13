package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.datos.repository.IComentarioArticuloRepository;
import co.com.ud.datos.service.ComentarioArticuloService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ComentarioArticuloServiceImplTest {

    private ComentarioArticuloService comentarioArticuloService;
    @Mock
    private IComentarioArticuloRepository iComentarioArticuloRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        comentarioArticuloService = new ComentarioArticuloServiceImpl(iComentarioArticuloRepository);
    }

    @Test
    public void testSaveSUCCESS(){
        ComentarioArticuloEntity entity = ComentarioArticuloEntity.builder()
                .comentario("Este es un comentario")
                .build();

        Mockito.doReturn(entity).when(iComentarioArticuloRepository).save(Mockito.any());

        Optional<ComentarioArticuloEntity> response = this.comentarioArticuloService.save(entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}