package co.com.ud.datos.controller;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.service.impl.IdeaServiceImpl;
import co.com.ud.utiles.dto.IdeaDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class IdeaControllerTest {

    private IdeaController ideaControler;
    @Mock
    private IdeaServiceImpl ideaServiceImpl;

    private ModelMapper mapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mapper = new ModelMapper();
        this.ideaControler = new IdeaController(ideaServiceImpl, mapper);
    }

    @Test
    public void testSave(){
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

        IdeaDto idea = IdeaDto.builder()
                .titulo("Este es un titulo")
                .contenido("Este es el contenido")
                .estado("CREADO")
                .id_profesor(1L)
                .usuarioId(1L)
                .build();
        Mockito.doReturn(Optional.of(response)).when(ideaServiceImpl).save(Mockito.any());

        ResponseEntity<IdeaDto> rta = ideaControler.save(idea);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.CREATED, rta.getStatusCode());
    }

    @Test
    public void testSaveFAILED(){
        IdeaDto idea = IdeaDto.builder()
                .titulo("Este es un titulo")
                .contenido("Este es el contenido")
                .estado("CREADO")
                .id_profesor(1L)
                .usuarioId(1L)
                .build();
        Mockito.doReturn(Optional.empty()).when(ideaServiceImpl).save(Mockito.any());

        ResponseEntity<IdeaDto> rta = ideaControler.save(idea);
        Assert.assertNotNull(rta);
        Assert.assertEquals(HttpStatus.NO_CONTENT, rta.getStatusCode());
    }

    @Test
    public void testGetIdeasByUsuarioSUCCESS(){

        List<IdeaEntity> ideas = new ArrayList<>();
        IdeaEntity ideaResponse = IdeaEntity.builder()
                .id(0L)
                .usuario(UsuarioEntity.builder().id(1L).build())
                .id_profesor(2L)
                .titulo("Titulo")
                .contenido("Este es el contenido")
                .build();
        ideas.add(ideaResponse);

        Mockito.doReturn(ideas).when(ideaServiceImpl).findByUsuarioId(Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaControler.getIdeasByUsuario(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetIdeasByUsuarioFAILED(){

        List<IdeaEntity> ideas = new ArrayList<>();

        Mockito.doReturn(ideas).when(ideaServiceImpl).findByUsuarioId(Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaControler.getIdeasByUsuario(1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetIdeasByProfesorAndEstadoEMPTY(){
        List<IdeaEntity> ideas = new ArrayList<>();
        Mockito.doReturn(ideas).when(ideaServiceImpl).findByProfesorIdAndEstado(Mockito.any(), Mockito.any());
        ResponseEntity<IdeaDto[]> response = ideaControler.getIdeasByProfesorAndEstado(0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetIdeasByProfesorAndEstadoSUCCESS(){
        List<IdeaEntity> ideas = new ArrayList<>();
        IdeaEntity idea = IdeaEntity.builder()
                .id(0L)
                .contenido("Esto es una prueba")
                .build();
        ideas.add(idea);

        Mockito.doReturn(ideas).when(ideaServiceImpl).findByProfesorIdAndEstado(Mockito.any(), Mockito.any());
        ResponseEntity<IdeaDto[]> response = ideaControler.getIdeasByProfesorAndEstado(0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateStatusIdeaByIdAndUsuarioSUCCESS(){

        Mockito.doReturn(Optional.of(true)).when(ideaServiceImpl).modificarIdProfAutorizaAndEstadoAndFechaAutoriza(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = ideaControler.updateStatusIdeaByIdAndUsuario(0L, "CREADA", 1l);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getBody());

    }

    @Test
    public void testUpdateStatusIdeaByIdAndUsuarioEMPTY(){

        Mockito.doReturn(Optional.empty()).when(ideaServiceImpl).modificarIdProfAutorizaAndEstadoAndFechaAutoriza(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = ideaControler.updateStatusIdeaByIdAndUsuario(0L, "CREADA", 1l);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    public void testUpdateIdeaSUCCESS(){

        IdeaDto dto = IdeaDto.builder()
                .id(1L)
                .id_profesor(2L)
                .estado("CREADA")
                .titulo("PRUEBA TITULO")
                .contenido("prueba contenido")
                .build();

        Mockito.doReturn(Optional.of(Boolean.TRUE)).when(ideaServiceImpl).modificaIdea(Mockito.any());

        ResponseEntity<Boolean> response = ideaControler.updateIdea(dto);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testUpdateStatusIdeaSUCCESS(){

        Mockito.doReturn(Optional.of(true)).when(ideaServiceImpl).modificarEstado(Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = ideaControler.updateStatusIdea(0L, "POR_CONFIRMAR_FORMATO");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getBody());
    }

    @Test
    public void testUpdateStatusIdeaFAILED(){

        Mockito.doReturn(Optional.of(false)).when(ideaServiceImpl).modificarEstado(Mockito.any(), Mockito.any());

        ResponseEntity<Boolean> response = ideaControler.updateStatusIdea(0L, "POR_CONFIRMAR_FORMATO");
        Assert.assertNotNull(response);
        Assert.assertFalse(response.getBody());
    }
}