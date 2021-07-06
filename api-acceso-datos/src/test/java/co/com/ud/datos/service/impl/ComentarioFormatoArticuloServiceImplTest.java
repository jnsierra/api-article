package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioFormatoArticuloEntity;
import co.com.ud.datos.repository.IComentarioFormatoArticuloRepository;
import co.com.ud.datos.service.ComentarioFormatoArticuloService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ComentarioFormatoArticuloServiceImplTest {

    private ComentarioFormatoArticuloService comentarioFormatoArticuloService;
    @Mock
    private IComentarioFormatoArticuloRepository comentarioFormatoArticuloRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioFormatoArticuloService = new ComentarioFormatoArticuloServiceImpl(comentarioFormatoArticuloRepository);
    }
    @Test
    public void testSave(){
        ComentarioFormatoArticuloEntity entity = ComentarioFormatoArticuloEntity.builder()
                .tipoComentario("RECHAZADO")
                .build();

        ComentarioFormatoArticuloEntity responseEntity = ComentarioFormatoArticuloEntity.builder()
                .id(1L)
                .tipoComentario("RECHAZADO")
                .build();
        Mockito.doReturn(responseEntity).when(comentarioFormatoArticuloRepository).save(Mockito.any());

        Optional<ComentarioFormatoArticuloEntity> response = this.comentarioFormatoArticuloService.save(entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }
}