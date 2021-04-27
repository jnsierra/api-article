package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ControlLecturaEntity;
import co.com.ud.datos.repository.IControlLecturaRepository;
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
public class ControlLecturaServiceImplTest {

    private ControlLecturaServiceImpl controlLecturaService;
    @Mock
    private IControlLecturaRepository controlLecturaRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.controlLecturaService = new ControlLecturaServiceImpl(controlLecturaRepository);
    }

    @Test
    public void testSaveSUCCESS(){
        ControlLecturaEntity responseEntity = ControlLecturaEntity.builder()
                .id(1L)
                .estado("CREADO")
                .autor("NICOLAS")
                .link("http://example.com")
                .comentario("Este es un comentario")
                .orden(1L)
                .year(2020L)
                .build();

        Mockito.doReturn(responseEntity).when(controlLecturaRepository).save(Mockito.any());

        ControlLecturaEntity entity = ControlLecturaEntity.builder()
                .estado("CREADO")
                .autor("NICOLAS")
                .link("http://example.com")
                .comentario("Este es un comentario")
                .orden(1L)
                .year(2020L)
                .build();

        Optional<ControlLecturaEntity> response = controlLecturaService.save(entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testGetByIdArticuloSUCCESS(){
        ControlLecturaEntity responseEntity = ControlLecturaEntity.builder()
                .id(1L)
                .estado("CREADO")
                .autor("NICOLAS")
                .link("http://example.com")
                .comentario("Este es un comentario")
                .orden(1L)
                .year(2020L)
                .build();
        ArrayList<ControlLecturaEntity> list = new ArrayList<>();
        list.add(responseEntity);
        Mockito.doReturn(list).when(controlLecturaRepository).getByIdArticulo(Mockito.any());


        List<ControlLecturaEntity> response = controlLecturaService.getByIdArticulo(1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }


}