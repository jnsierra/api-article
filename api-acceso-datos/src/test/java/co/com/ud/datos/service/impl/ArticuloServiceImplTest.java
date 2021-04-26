package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IArticuloRepository;
import co.com.ud.datos.service.ArticuloService;
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
public class ArticuloServiceImplTest {

    @Mock
    private IArticuloRepository articuloRepository;
    private ArticuloService articuloService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        articuloService = new ArticuloServiceImpl(articuloRepository);
    }

    @Test
    public void testFindByIdIdeaEMPTY(){
        Mockito.doReturn(Optional.empty()).when(articuloRepository).findByIdIdea(Mockito.any());
        Optional<ArticuloEntity> response = articuloService.findByIdIdea(0L);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testSave(){
        ArticuloEntity response = ArticuloEntity.builder()
                .id(1L)
                .estado("CREADA")
                .idea(IdeaEntity.builder().id(1L).build())
                .titulo("Este es el titulo")
                .resumen("Este es el resumen")
                .build();
        Mockito.doReturn(response).when(articuloRepository).save(Mockito.any());

        ArticuloEntity entity = ArticuloEntity.builder()
                .estado("CREADA")
                .idea(IdeaEntity.builder().id(1L).build())
                .titulo("Este es el titulo")
                .resumen("Este es el resumen")
                .build();
        Optional<ArticuloEntity> responseEntity = articuloService.save(entity);
        Assert.assertNotNull(responseEntity);
        Assert.assertTrue(responseEntity.isPresent());
    }

    @Test
    public void testGetArticulosByUserSUCCESS(){
        ArticuloEntity entity = ArticuloEntity.builder()
                .contenido("Este es el contenido")
                .estado("ENVIADO_POR_CORRECCIONES")
                .resumen_ingles("this")
                .resumen("esto")
                .idea(IdeaEntity.builder()
                        .id(1L)
                        .usuario(UsuarioEntity.builder()
                                .id(1L)
                                .build())
                        .build())
                .build();
        List<ArticuloEntity> listEntity = new ArrayList<>();
        listEntity.add(entity);
        Mockito.doReturn(listEntity).when(articuloRepository).getArticulosByUser(Mockito.any());

        List<ArticuloEntity> response = articuloService.getArticulosByUser(1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void testGetById(){
        ArticuloEntity entity = ArticuloEntity.builder()
                .contenido("Este es el contenido")
                .estado("ENVIADO_POR_CORRECCIONES")
                .resumen_ingles("this")
                .resumen("esto")
                .idea(IdeaEntity.builder()
                        .id(1L)
                        .usuario(UsuarioEntity.builder()
                                .id(1L)
                                .build())
                        .build())
                .build();

        Mockito.doReturn(Optional.of(entity)).when(articuloRepository).findById(1L);

        Optional<ArticuloEntity> response = articuloService.getById(1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}