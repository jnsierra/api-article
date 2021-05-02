package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.entity.ParrafoEntity;
import co.com.ud.datos.repository.IParrafoRepository;
import co.com.ud.datos.service.ParrafoService;
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
public class ParrafoServiceImplTest {

    private ParrafoService parrafoService;

    @Mock
    private IParrafoRepository parrafoRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.parrafoService = new ParrafoServiceImpl(parrafoRepository);
    }

    @Test
    public void testSaveSUCCESS(){
        ParrafoEntity responseEntity = ParrafoEntity.builder()
                .id(1L)
                .estado("CREADO")
                .contenido("Este es el contenido")
                .articulo(ArticuloEntity.builder()
                        .id(1L)
                        .build())
                .orden(1L)
                .build();
        Mockito.doReturn(responseEntity).when(parrafoRepository).save(Mockito.any());
        Optional<ParrafoEntity> response = this.parrafoService.save(responseEntity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());

    }

    @Test
    public void testGetParrafoByArticulo(){
        ParrafoEntity responseEntity = ParrafoEntity.builder()
                .id(1L)
                .estado("CREADO")
                .contenido("Este es el contenido")
                .articulo(ArticuloEntity.builder()
                        .id(1L)
                        .build())
                .orden(1L)
                .build();
        ArrayList<ParrafoEntity> list = new ArrayList<>();
        list.add(responseEntity);
        Mockito.doReturn(list).when(parrafoRepository).getParrafoByArticulo(Mockito.any());
        List<ParrafoEntity> response = parrafoService.getParrafoByArticulo(1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }

}