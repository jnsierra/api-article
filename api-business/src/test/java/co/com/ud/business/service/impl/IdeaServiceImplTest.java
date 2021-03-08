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

}