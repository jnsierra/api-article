package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.repository.IIdeaRepository;
import co.com.ud.datos.service.IdeaService;
import co.com.ud.utiles.enumeracion.TYPE_PROFESOR;
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
    public void testFindByUsuarioEstado(){
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
        Mockito.doReturn(list).when(ideaRepository).findByEstado(Mockito.any());

        List<IdeaEntity> rta = ideaService.findByEstado("CREADO");
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

        List<IdeaEntity> rta = ideaService.findByProfesorIdAndEstado(0L, "CREADO", TYPE_PROFESOR.TUTOR);
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
    @Test
    public void testModificaIdeaSUCCESS(){
        Mockito.doReturn(1).when(ideaRepository).modificarIdea(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        IdeaEntity entity = IdeaEntity.builder()
                .id(1L)
                .id_profesor(2L)
                .estado("CREADA")
                .titulo("PRUEBA TITULO")
                .contenido("prueba contenido")
                .build();
        Optional<Boolean> response = ideaService.modificaIdea(entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testModificaIdeaFAILED(){
        Mockito.doReturn(0).when(ideaRepository).modificarIdea(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        IdeaEntity entity = IdeaEntity.builder()
                .id(1L)
                .id_profesor(2L)
                .estado("CREADA")
                .titulo("PRUEBA TITULO")
                .contenido("prueba contenido")
                .build();
        Optional<Boolean> response = ideaService.modificaIdea(entity);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertFalse(response.get());
    }

    @Test
    public void testModificaEstadoSUCCESS(){
        Mockito.doReturn(1).when(ideaRepository).modificarEstado(Mockito.any(), Mockito.any());

        Optional<Boolean> response = ideaService.modificarEstado(1L, "POR_CONFIRMAR_FORMATO");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testModificaEstadoFAILED(){
        Mockito.doReturn(0).when(ideaRepository).modificarEstado(Mockito.any(), Mockito.any());

        Optional<Boolean> response = ideaService.modificarEstado(1L, "POR_CONFIRMAR_FORMATO");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertFalse(response.get());
    }

    @Test
    public void testModificarJuradoSUCCESS(){
        Mockito.doReturn(1).when(ideaRepository).modificarJurado(Mockito.any(), Mockito.any());
        Optional<Boolean> response = ideaService.modificarJurado(1L, 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.get());
    }

    @Test
    public void testModificarJuradoEMPTY(){
        Mockito.doReturn(0).when(ideaRepository).modificarJurado(Mockito.any(), Mockito.any());
        Optional<Boolean> response = ideaService.modificarJurado(1L, 1L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.get());
    }

}