package co.com.ud.business.controller;

import co.com.ud.business.service.IdeaService;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.IdeaCompletoDto;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.USER_STATE;
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

    @Mock
    private IdeaService ideaService;
    @Mock
    private UsuarioService usuarioService;
    private ModelMapper modelMapper;
    private IdeaController ideaController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.modelMapper = new ModelMapper();
        this.ideaController = new IdeaController(ideaService, modelMapper, usuarioService);
    }

    @Test
    public void testGetIdeasByUsuarioSUCCESS(){
        List<IdeaDto> respuesta = new ArrayList<>();
        IdeaDto rta = IdeaDto.builder()
                .id(1L)
                .usuarioId(1L)
                .build();
        respuesta.add(rta);

        Mockito.doReturn(respuesta).when(ideaService).findIdeasByUsuario(Mockito.any(), Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaController.getIdeasByUsuario("1234",1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetIdeasByUsuarioFAILED(){
        List<IdeaDto> respuesta = new ArrayList<>();
        IdeaDto rta = IdeaDto.builder()
                .id(1L)
                .usuarioId(1L)
                .build();
        respuesta.add(rta);

        Mockito.doReturn(respuesta).when(ideaService).findIdeasByUsuario(Mockito.any(),Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaController.getIdeasByUsuario("1234",1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetIdeasByUsuarioEMPTY(){

        Mockito.doReturn(null).when(ideaService).findIdeasByUsuario(Mockito.any(), Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaController.getIdeasByUsuario("1234",1L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetIdeasByProfesorAndEstadoEMPTY(){

        Mockito.doReturn(null).when(ideaService).findByProfesorIdAndEstado(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaController.getIdeasByProfesorAndEstado("123",0L, "CREADO");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    public void testGetIdeasByProfesorAndEstadoSUCCESS(){

        List<IdeaDto> ideas = new ArrayList<>();
        IdeaDto idea = IdeaDto.builder()
                .id(0L)
                .id_profesor(1L)
                .contenido("Esto es una prueba")
                .build();
        ideas.add(idea);

        Mockito.doReturn(ideas).when(ideaService).findByProfesorIdAndEstado(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<IdeaDto[]> response = ideaController.getIdeasByProfesorAndEstado("123",0L, "CREADO");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testGetByIdEMPTY_IDEA(){
        Mockito.doReturn(Optional.empty()).when(ideaService).findById(Mockito.any(), Mockito.any());

        ResponseEntity<IdeaCompletoDto> response = ideaController.getById("1234",0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetByIdEMPTY_USER(){
        IdeaDto idea = IdeaDto.builder()
                .id(1L)
                .contenido("Prueba de contenido")
                .usuarioId(1L)
                .build();

        Mockito.doReturn(Optional.of(idea)).when(ideaService).findById(Mockito.any(), Mockito.any());

        Mockito.doReturn(Optional.empty()).when(usuarioService).getUserById(Mockito.any());

        ResponseEntity<IdeaCompletoDto> response = ideaController.getById("1234",0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetByIdSUCCESS(){
        IdeaDto idea = IdeaDto.builder()
                .id(1L)
                .contenido("Prueba de contenido")
                .usuarioId(1L)
                .build();

        Mockito.doReturn(Optional.of(idea)).when(ideaService).findById(Mockito.any(), Mockito.any());

        UsuarioDto usuario = UsuarioDto.builder()
                .id(1L)
                .nombre("Jesus Nicolas")
                .estado(USER_STATE.ACTIVO)
                .build();

        Mockito.doReturn(Optional.of(usuario)).when(usuarioService).getUserById(Mockito.any());

        ResponseEntity<IdeaCompletoDto> response = ideaController.getById("123445",0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}