package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.repository.IArticuloRepository;
import co.com.ud.datos.service.ArticuloService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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

}