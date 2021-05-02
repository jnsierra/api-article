package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.PersonaDto;
import co.com.ud.utiles.dto.ProfesoresIdeaDto;
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

import java.util.List;
import java.util.Optional;

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

        Mockito.doReturn(ideaResposne).when(ideaCliente).getIdeasByUsuario(Mockito.any(),Mockito.any());

        UsuarioDto usuario = UsuarioDto.builder()
                .id(1L)
                .persona(PersonaDto.builder().id(1L).apellidos("Canon Mora").nombres("Luisa Maria").build())
                .build();

        Mockito.doReturn(new ResponseEntity<>(usuario, HttpStatus.OK)).when(usuarioCliente).getUserById(Mockito.any());

        List<IdeaDto> respuesta = ideaService.findIdeasByUsuario("123",1L);
        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.isEmpty());
    }

    @Test
    public void testFindIdeasByUsuarioEMPTY_USUARIO(){
        ResponseEntity<IdeaDto[]> ideaResposne = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Mockito.doReturn(ideaResposne).when(ideaCliente).getIdeasByUsuario(Mockito.any(),Mockito.any());

        List<IdeaDto> respuesta = ideaService.findIdeasByUsuario("123",1L);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(0, respuesta.size());
    }

    @Test
    public void testFindByProfesorIdAndEstadoEMPTY(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(ideaCliente).getIdeasByProfesorAndEstado(Mockito.any(),Mockito.any(), Mockito.any());

        List<IdeaDto> response = ideaService.findByProfesorIdAndEstado("123", 0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());

    }

    @Test
    public void testFindByProfesorIdAndEstadoEMPTY_LIST(){
        IdeaDto[] ideas = new IdeaDto[0];

        Mockito.doReturn(new ResponseEntity<>(ideas,HttpStatus.OK)).when(ideaCliente).getIdeasByProfesorAndEstado(Mockito.any(),Mockito.any(), Mockito.any());

        List<IdeaDto> response = ideaService.findByProfesorIdAndEstado("123",0L, "CREADA");
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

        Mockito.doReturn(new ResponseEntity<>(ideas,HttpStatus.OK)).when(ideaCliente).getIdeasByProfesorAndEstado(Mockito.any(), Mockito.any(), Mockito.any());

        List<IdeaDto> response = ideaService.findByProfesorIdAndEstado("123",0L, "CREADA");
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }

    @Test
    public void testFindByIdEMPTY(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(ideaCliente).getById(Mockito.any(),Mockito.any());

        Optional<IdeaDto> response = ideaService.findById("123456",1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testFindByIdEMPTY_USER(){
        IdeaDto ideaDto = IdeaDto.builder()
                .id(1L)
                .usuarioId(1L)
                .id_profesor(2L)
                .idProfesorAutoriza(3L)
                .build();

        Mockito.doReturn(new ResponseEntity<>(ideaDto, HttpStatus.OK)).when(ideaCliente).getById(Mockito.any(), Mockito.any());

        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(usuarioCliente).getUserById(Mockito.any());

        Optional<IdeaDto> response = ideaService.findById("123456",1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testFindByIdSUCCESS(){
        IdeaDto ideaDto = IdeaDto.builder()
                .id(1L)
                .usuarioId(1L)
                .id_profesor(2L)
                .idProfesorAutoriza(3L)
                .build();

        Mockito.doReturn(new ResponseEntity<>(ideaDto, HttpStatus.OK)).when(ideaCliente).getById(Mockito.any(), Mockito.any());

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .persona(PersonaDto.builder()
                        .apellidos("Sierra")
                        .nombres("Jesus")
                        .build())
                .build();

        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserById(Mockito.any());

        Optional<IdeaDto> response = ideaService.findById("123456",1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testUpdateStatusSUCCESS(){
        Mockito.doReturn(new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK)).when(ideaCliente).updateStatusIdea(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<Boolean> response = ideaService.updateStatus("dgfgfdhw5", 1L, "ESPERA_JURADO");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void testUpdateStatusEMPTY(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(ideaCliente).updateStatusIdea(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<Boolean> response = ideaService.updateStatus("dgfgfdhw5", 1L, "ESPERA_JURADO");
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }

    @Test
    public void testFindByEstadoSUCCESS(){

        IdeaDto[] responseEntity = new IdeaDto[1];
        IdeaDto item = IdeaDto.builder()
                .id(1L)
                .build();
        responseEntity[0] = item;
        Mockito.doReturn(new ResponseEntity<>(responseEntity, HttpStatus.OK)).when(ideaCliente).getIdeasByEstado(Mockito.any(), Mockito.any());

        UsuarioDto entity = UsuarioDto.builder()
                .id(1L)
                .persona(PersonaDto.builder()
                        .id(1L)
                        .nombres("Jesus Nicolas")
                        .apellidos("Sierra")
                        .build())
                .build();

        Mockito.doReturn(new ResponseEntity<>(entity,HttpStatus.OK)).when(usuarioCliente).getUserById(Mockito.any());


        List<IdeaDto> response = ideaService.findByEstado("dgfdsghdf54", "ESPERA_JURADO");
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());

    }

    @Test
    public void testGetProfesoresByIdIdeaSUCCESS(){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .persona(PersonaDto.builder()
                        .apellidos("Sierra")
                        .nombres("Jesus")
                        .build())
                .build();

        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserById(Mockito.any());
        IdeaDto item = IdeaDto.builder()
                .id(1L)
                .idProfesorAutoriza(1L)
                .id_profesor(2L)
                .idProfesorJurado(3L)
                .build();

        Mockito.doReturn(new ResponseEntity<>(item, HttpStatus.OK)).when(ideaCliente).getById(Mockito.any(), Mockito.any());

        Optional<ProfesoresIdeaDto> response = ideaService.getProfesoresByIdIdea("sadgff3h21", 1L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}