package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.PersonaDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class IdeaServiceImplTest {

    private IdeaServiceImpl ideaService;
    @Mock
    private IdeaCliente ideaCliente;
    @Mock
    private UsuarioCliente usuarioCliente;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ideaService = new IdeaServiceImpl(ideaCliente, usuarioCliente);
    }

    @Test
    public void testFindIdeasByUsuarioSUCCESS(){
        IdeaDto[] arrayIdea = new IdeaDto[1];
        IdeaDto item = IdeaDto.builder()
                .id(1L)
                .usuarioId(1L)
                .id_profesor(2L)
                .build();
        arrayIdea[0] = item;
        ResponseEntity<IdeaDto[]> ideaResposne = new ResponseEntity<>(arrayIdea, HttpStatus.OK);

        Mockito.doReturn(ideaResposne).when(ideaCliente).getIdeasByUsuario(Mockito.any());

        UsuarioDto usuario = UsuarioDto.builder()
                .id(1L)
                .persona(PersonaDto.builder().id(1L).apellidos("Canon Mora").nombres("Luisa Maria").build())
                .build();

        Mockito.doReturn(new ResponseEntity<>(usuario, HttpStatus.OK)).when(usuarioCliente).getUserById(Mockito.any());

        List<IdeaDto> respuesta = ideaService.findIdeasByUsuario(1L);
        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.isEmpty());
    }

    @Test
    public void testFindIdeasByUsuarioEMPTY_USUARIO(){
        ResponseEntity<IdeaDto[]> ideaResposne = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Mockito.doReturn(ideaResposne).when(ideaCliente).getIdeasByUsuario(Mockito.any());

        List<IdeaDto> respuesta = ideaService.findIdeasByUsuario(1L);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(0, respuesta.size());
    }

    @Test
    public void testFindByProfesorIdAndEstadoEMPTY(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(ideaCliente).getIdeasByProfesorAndEstado(Mockito.any(), Mockito.any());

        List<IdeaDto> response = ideaService.findByProfesorIdAndEstado(0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());

    }

    @Test
    public void testFindByProfesorIdAndEstadoEMPTY_LIST(){
        IdeaDto[] ideas = new IdeaDto[0];

        Mockito.doReturn(new ResponseEntity<>(ideas,HttpStatus.OK)).when(ideaCliente).getIdeasByProfesorAndEstado(Mockito.any(), Mockito.any());

        List<IdeaDto> response = ideaService.findByProfesorIdAndEstado(0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());

    }

    @Test
    public void testFindByProfesorIdAndEstadoSUCCESS(){
        IdeaDto[] ideas = new IdeaDto[1];
        IdeaDto idea = IdeaDto.builder()
                .id(1L)
                .usuarioId(2L)
                .build();
        ideas[0] = idea;

        UsuarioDto usu = UsuarioDto.builder()
                .id(2L)
                .nombre("Jesus Nicolas")
                .persona(PersonaDto.builder().nombres("Jesus Nicolas").apellidos("Sierra Chaparro").build())
                .build();
        ResponseEntity<UsuarioDto> responseUsu = new ResponseEntity<>(usu, HttpStatus.OK);
        Mockito.doReturn(responseUsu).when(usuarioCliente).getUserById(Mockito.any());

        Mockito.doReturn(new ResponseEntity<>(ideas,HttpStatus.OK)).when(ideaCliente).getIdeasByProfesorAndEstado(Mockito.any(), Mockito.any());

        List<IdeaDto> response = ideaService.findByProfesorIdAndEstado(0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }

}