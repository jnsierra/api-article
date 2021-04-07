package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.FormatoIdeaEntity;
import co.com.ud.datos.repository.IFormatoIdeaRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FormatoIdeaServiceImplTest {

    private FormatoIdeaServiceImpl formatoIdeaService;
    @Mock
    private IFormatoIdeaRepository iFormatoIdeaRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.formatoIdeaService = new FormatoIdeaServiceImpl(iFormatoIdeaRepository);
    }

    @Test
    public void testSave(){
        FormatoIdeaEntity entity = FormatoIdeaEntity
                .builder()
                .formato("APROBACION_COORDINACION")
                .nombre("FORMATO_FIRMADO")
                .ubicacion("/opt/formato_1.pdf")
                .build();

        FormatoIdeaEntity response = FormatoIdeaEntity
                .builder()
                .id(1L)
                .formato("APROBACION_COORDINACION")
                .nombre("FORMATO_FIRMADO")
                .ubicacion("/opt/formato_1.pdf")
                .build();

        Mockito.doReturn(response).when(iFormatoIdeaRepository).save(Mockito.any());

        Optional<FormatoIdeaEntity> rta = formatoIdeaService.save(entity);
        Assert.assertNotNull(rta);
        Assert.assertTrue(rta.isPresent());
    }

}