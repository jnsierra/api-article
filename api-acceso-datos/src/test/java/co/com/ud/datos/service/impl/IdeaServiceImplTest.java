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
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void testFindByUsuarioId(){
        ArrayList<IdeaEntity> list = new ArrayList<>();
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
        list.add(response);
        Mockito.doReturn(list).when(ideaRepository).findByUsuarioId(Mockito.any());

        List<IdeaEntity> rta = ideaService.findByUsuarioId(Mockito.any());
        Assert.assertNotNull(rta);
        Assert.assertFalse(rta.isEmpty());
    }

    @Test
    public void testFindByProfesorIdAndEstado(){
        ArrayList<IdeaEntity> list = new ArrayList<>();
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
        list.add(response);
        Mockito.doReturn(list).when(ideaRepository).findByProfesorIdAndEstado(Mockito.any(), Mockito.any());

        List<IdeaEntity> rta = ideaService.findByProfesorIdAndEstado(0L, "CREADO");
        Assert.assertNotNull(rta);
        Assert.assertFalse(rta.isEmpty());

    }

    @Test
    public void testModificarIdProfAutorizaAndEstadoAndFechaAutorizaFAILED(){

        Mockito.doReturn(0).when(ideaRepository).modificarIdProfAutorizaAndEstadoAndFechaAutoriza(Mockito.any(), Mockito.any(), Mockito.any(),Mockito.any() );

        Optional<Boolean> rta = ideaService.modificarIdProfAutorizaAndEstadoAndFechaAutoriza(0L, 1L, "CREADA");
        Assert.assertNotNull(rta);
        Assert.assertFalse(rta.isPresent());

    }

    @Test
    public void testModificarIdProfAutorizaAndEstadoAndFechaAutorizaSUCCESS(){

        Mockito.doReturn(1).when(ideaRepository).modificarIdProfAutorizaAndEstadoAndFechaAutoriza(Mockito.any(), Mockito.any(), Mockito.any(),Mockito.any() );

        Optional<Boolean> rta = ideaService.modificarIdProfAutorizaAndEstadoAndFechaAutoriza(0L, 1L, "CREADA");
        Assert.assertNotNull(rta);
        Assert.assertTrue(rta.isPresent());

    }

}