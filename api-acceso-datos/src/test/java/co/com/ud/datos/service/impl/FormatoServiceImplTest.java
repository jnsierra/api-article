package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.FormatoEntity;
import co.com.ud.datos.repository.IFormatoRepository;
import co.com.ud.datos.service.FormatoService;
import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FormatoServiceImplTest {

    private FormatoService formatoService;
    @Mock
    private IFormatoRepository formatoRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        formatoService = new FormatoServiceImpl(formatoRepository);
    }
    @Test
    public void testSave(){
        FormatoEntity entity = FormatoEntity.builder()
                .id(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .estado("ACTIVO")
                .nombre("ARTICULO.docx")
                .build();
        FormatoEntity responseEntity = FormatoEntity.builder()
                .id(1L)
                .formato(TYPE_FORMATO_ARTICULO.ARTICULO_POR_CORREGIR)
                .estado("ACTIVO")
                .nombre("ARTICULO.docx")
                .build();

        Mockito.doReturn(responseEntity).when(formatoRepository).save(Mockito.any());

        Optional<FormatoEntity> response = formatoService.save(entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}