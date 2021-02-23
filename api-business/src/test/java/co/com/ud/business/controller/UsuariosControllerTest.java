package co.com.ud.business.controller;


import co.com.ud.business.service.UsuarioService;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuariosControllerTest {

    @Mock
    private UsuarioService usuarioService;
    private UsuariosController usuariosController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.usuariosController = new UsuariosController(usuarioService);
    }

    @Test
    public void getUsersByEmailSUCCESS(){

        UsuarioDto usuarioResponse = UsuarioDto.builder()
                .id(0L)
                .nombre("Luisa Maria")
                .build();

        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioService).getUsuarioByCorreo(Mockito.any());

        ResponseEntity<UsuarioDto> response = usuariosController.getUsersByEmail("jnsierrac@gmail.com");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUsersByEmailEMPTY(){
        Mockito.doReturn(Optional.empty()).when(usuarioService).getUsuarioByCorreo(Mockito.any());

        ResponseEntity<UsuarioDto> response = usuariosController.getUsersByEmail("jnsierrac@gmail.com");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void getUsersSUCCESS(){
        List<UsuarioDto> usuarios =new ArrayList<>();
        UsuarioDto usuario = UsuarioDto.builder()
                .id(0l)
                .nombre("Jesus")
                .correo("jnsierrac@gmail.com")
                .build();
        usuarios.add(usuario);

        Mockito.doReturn(usuarios).when(usuarioService).getUsers();

        ResponseEntity<List> response = usuariosController.getUsers();
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUsersEMPTY(){
        List<UsuarioDto> usuarios =new ArrayList<>();
        Mockito.doReturn(usuarios).when(usuarioService).getUsers();

        ResponseEntity<List> response = usuariosController.getUsers();
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void getUserByIdSUCCESS(){

        UsuarioDto usuarioResponse = UsuarioDto.builder()
                .id(0L)
                .nombre("Luisa Maria")
                .build();

        Mockito.doReturn(Optional.of(usuarioResponse)).when(usuarioService).getUserById(0L);

        ResponseEntity<UsuarioDto> response = usuariosController.getUserById(0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUserByIdEMPTY(){
        Mockito.doReturn(Optional.empty()).when(usuarioService).getUserById(0L);

        ResponseEntity<UsuarioDto> response = usuariosController.getUserById(0L);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}