package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IIdeaRepository;
import co.com.ud.datos.service.IdeaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class IdeaServiceImplTest {
    @Mock
    private IIdeaRepository ideaRepository;

    private IdeaService ideaService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.ideaService = new IdeaServiceImpl(ideaRepository);
    }

    @Test
    public void testSave(){
        IdeaEntity request = IdeaEntity.builder()
                .titulo("Este es un titulo")
                .contenido("Este es el contenido")
                .estado("CREADO")
                .id_profesor(1L)
                .usuario(UsuarioEntity.builder()
                        .id(1L)
                        .build())
                .build();
        IdeaEntity response = IdeaEntity.builder()
                .id(1L)
                .titulo("Este es un titulo")
                .contenido("Este es el contenido")
                .estado("CREADO")
                .id_profesor(1L)
                .usuario(UsuarioEntity.builder()
                        .id(1L)
                        .build())
                .build();
        Mockito.doReturn(response).when(ideaRepository).save(Mockito.any());


        Optional<IdeaEntity> rta = ideaService.save(request);
        Assert.assertNotNull(rta);
        Assert.assertTrue(rta.isPresent());
    }

}