package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.datos.repository.IComentarioRepository;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ComentarioServiceImplTest {

    private ComentarioServiceImpl comentarioService;
    @Mock
    private IComentarioRepository comentarioRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.comentarioService = new ComentarioServiceImpl(comentarioRepository);
    }

    @Test
    public void testSave(){
        ComentarioEntity response = ComentarioEntity.builder()
                .id(1L)
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .build();
        Mockito.doReturn(response).when(comentarioRepository).save(Mockito.any());

        ComentarioEntity entity = ComentarioEntity.builder()
                .comentario("Prueba")
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_FORMATO_IDEA)
                .llave(2L)
                .id_usuario(2L)
                .build();

        Optional<ComentarioEntity> rta = comentarioService.save(entity);
        Assert.assertNotNull(rta);
        Assert.assertTrue(rta.isPresent());
    }


}