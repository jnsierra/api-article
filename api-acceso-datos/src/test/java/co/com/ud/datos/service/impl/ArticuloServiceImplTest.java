package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
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

}