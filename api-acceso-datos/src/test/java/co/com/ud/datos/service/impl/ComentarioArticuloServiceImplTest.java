package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.datos.repository.IComentarioArticuloRepository;
import co.com.ud.datos.service.ComentarioArticuloService;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;
import org.checkerframework.checker.nullness.Opt;
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

    @Test
    public void testFindByTypeAndArtEMPTY(){
        Mockito.doReturn(new ArrayList<>()).when(iComentarioArticuloRepository).findByTypeComentarioArtAndArticulo(Mockito.any(), Mockito.any());

        List<ComentarioArticuloEntity> response = comentarioArticuloService.findByTypeAndArt(TYPE_COMMENTS_ARTICLE.TITULO, 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testFindByTypeAndArtSUCCESS(){

        ComentarioArticuloEntity entity = ComentarioArticuloEntity.builder()
                .id(1L)
                .typeComentarioArt(TYPE_COMMENTS_ARTICLE.TITULO)
                .build();

        List<ComentarioArticuloEntity> list = new ArrayList<>();
        list.add(entity);

        Mockito.doReturn(list).when(iComentarioArticuloRepository).findByTypeComentarioArtAndArticulo(Mockito.any(), Mockito.any());

        List<ComentarioArticuloEntity> response = comentarioArticuloService.findByTypeAndArt(TYPE_COMMENTS_ARTICLE.TITULO, 1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void testUpdateRespuestaComentarioSUCCESS(){
        ComentarioArticuloEntity comentario = ComentarioArticuloEntity.builder()
                .id(1L)
                .typeComentarioArt(TYPE_COMMENTS_ARTICLE.INTRODUCCION)
                .build();
        Mockito.doReturn(Optional.of(comentario)).when(iComentarioArticuloRepository).findById(Mockito.any());

        Mockito.doReturn(comentario).when(iComentarioArticuloRepository).save(Mockito.any());

        Optional<Boolean> response = comentarioArticuloService.updateRespuestaComentario(1L, "Este es el comentario");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertTrue(response.get());
    }

    @Test
    public void testFindByArtIdSUCCESS(){

        ComentarioArticuloEntity entity = ComentarioArticuloEntity.builder()
                .id(1L)
                .typeComentarioArt(TYPE_COMMENTS_ARTICLE.TITULO)
                .build();

        List<ComentarioArticuloEntity> list = new ArrayList<>();
        list.add(entity);

        Mockito.doReturn(list).when(iComentarioArticuloRepository).findByArticulo(Mockito.any());

        List<ComentarioArticuloEntity> response = comentarioArticuloService.findByArtId( 1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }

}