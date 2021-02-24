package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.utiles.dto.UsuarioDto;
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
public class UsuarioServiceImplTest {

    private UsuarioServiceImpl usuarioService;
    @Mock
    private UsuarioCliente usuarioCliente;
    private ModelMapper map;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioCliente);
        this.map = new ModelMapper();
    }

    @Test
    public void getUsuarioByCorreoSUCCESS(){
        UsuarioDto response = UsuarioDto.builder()
                .id(0L)
                .correo("jnsierrac@gmail.com")
                .build();

        Mockito.doReturn(new ResponseEntity<>(response, HttpStatus.OK) ).when(usuarioCliente).getUserByEmail("jnsierrac@gmail.com");

        Optional<UsuarioDto> usuario = usuarioService.getUsuarioByCorreo("jnsierrac@gmail.com");
        Assert.assertNotNull(usuario);
        Assert.assertTrue(usuario.isPresent());
    }

    @Test
    public void getUsuarioByCorreoEMPTY(){

        Mockito.doReturn(new ResponseEntity<>( HttpStatus.NO_CONTENT) ).when(usuarioCliente).getUserByEmail("jnsierrac@gmail.com");

        Optional<UsuarioDto> usuario = usuarioService.getUsuarioByCorreo("jnsierrac@gmail.com");
        Assert.assertNotNull(usuario);
        Assert.assertFalse(usuario.isPresent());
    }
    @Test
    public void getUsersSUCCESS(){
        UsuarioDto usuario = UsuarioDto.builder()
                .id(0L)
                .nombre("Jesus Nicolas")
                .build();
        List<UsuarioDto> listUser = new ArrayList();
        listUser.add(usuario);
        Mockito.doReturn(new ResponseEntity<UsuarioDto[]>( map.map(listUser, UsuarioDto[].class), HttpStatus.OK )).when(usuarioCliente).get();

        List<UsuarioDto> response = usuarioService.getUsers();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void getUsersEMPTY(){
        Mockito.doReturn(new ResponseEntity<UsuarioDto[]>( HttpStatus.NO_CONTENT )).when(usuarioCliente).get();
        List<UsuarioDto> response = usuarioService.getUsers();
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void getUserByIdSUCCESS(){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .id(0L)
                .nombre("Jesus")
                .build();
        Mockito.doReturn(new ResponseEntity<UsuarioDto>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserById(0L);
        Optional<UsuarioDto> response = usuarioService.getUserById(0L);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

    @Test
    public void getUserByIdEMPTY(){
        Mockito.doReturn(new ResponseEntity<UsuarioDto>(HttpStatus.NO_CONTENT)).when(usuarioCliente).getUserById(0L);
        Optional<UsuarioDto> response = usuarioService.getUserById(0L);
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isPresent());
    }
    @Test
    public void save(){
        UsuarioDto usuario = UsuarioDto.builder()
                .nombre("Luisa Maria")
                .correo("jnsierrac@gmail.com")
                .contrasena("123456")
                .build();
        UsuarioDto usuarioResponse = UsuarioDto.builder()
                .nombre("Luisa Maria")
                .correo("jnsierrac@gmail.com")
                .contrasena("123456")
                .build();
        Mockito.doReturn( new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED) ).when(usuarioCliente).save(Mockito.any());

        Optional<UsuarioDto> response = usuarioService.save(usuario);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());

    }
}