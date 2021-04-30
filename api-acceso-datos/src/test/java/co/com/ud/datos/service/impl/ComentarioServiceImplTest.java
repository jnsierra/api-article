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

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void testFindByLlaveAndTipoComentario() {
        List<ComentarioEntity> comentarios = new ArrayList<>();
        ComentarioEntity comentario = ComentarioEntity.builder()
                .id(1L)
                .id_usuario(1L)
                .llave(1L)
                .tipo_comentario(TYPE_COMMENTS.RECHAZO_IDEA)
                .comentario("PRUEBA")
                .build();
        comentarios.add(comentario);

        Mockito.doReturn(comentarios).when(comentarioRepository).findByLlaveAndTipoComentario(Mockito.any(), Mockito.any());

        List<ComentarioEntity> rta = comentarioService.findByLlaveAndTipoComentario(1L, TYPE_COMMENTS.RECHAZO_FORMATO_IDEA);
        Assert.assertNotNull(rta);
    }

    @Test
    public void testUpdateComentarioUbicacionSUCCESS(){
        Mockito.doReturn(1).when(comentarioRepository).updateComentarioUbicacion(Mockito.any(),Mockito.any());

        Optional<Boolean> response = comentarioService.updateComentarioUbicacion(1L, "/opt/1_comentario.pdf");
        Assert.assertNotNull(response);
        Assert.assertEquals(Boolean.TRUE, response.get());
    }

    @Test
    public void testUpdateComentarioUbicacionEMPTY(){
        Mockito.doReturn(0).when(comentarioRepository).updateComentarioUbicacion(Mockito.any(),Mockito.any());

        Optional<Boolean> response = comentarioService.updateComentarioUbicacion(1L, "/opt/1_comentario.pdf");
        Assert.assertNotNull(response);
        Assert.assertEquals(Boolean.FALSE, response.get());
    }

}