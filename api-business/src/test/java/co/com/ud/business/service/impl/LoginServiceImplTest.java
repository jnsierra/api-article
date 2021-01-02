package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class LoginServiceImplTest {

    private LoginServiceImpl loginService;
    @Mock
    private UsuarioCliente usuarioCliente;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.loginService = new LoginServiceImpl(usuarioCliente);
    }

    @Test
    public void testValidaLoginSUCCESS(){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .estado(USER_STATE.ACTIVO)
                .cambioContra("N")
                .build();

        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmailAndPass(Mockito.any(), Mockito.any());
        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmail(Mockito.any());

        LOGIN_ACTION rta = loginService.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.SUCCESS, rta);
    }

    @Test
    public void testValidaLoginSUCCESS_CHANGE_PASSWORD(){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .estado(USER_STATE.ACTIVO)
                .cambioContra("S")
                .build();

        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmailAndPass(Mockito.any(), Mockito.any());
        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmail(Mockito.any());

        LOGIN_ACTION rta = loginService.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.SUCCESS_CHANGE_PASSWORD, rta);
    }

    @Test
    public void testValidaLoginPASSWORD_INCORRECT(){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .estado(USER_STATE.ACTIVO)
                .cambioContra("S")
                .build();

        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(usuarioCliente).getUserByEmailAndPass(Mockito.any(), Mockito.any());
        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmail(Mockito.any());

        LOGIN_ACTION rta = loginService.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.PASSWORD_INCORRECT, rta);
    }

    @Test
    public void testValidaLoginUSER_BLOCKED(){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .estado(USER_STATE.INACTIVO)
                .cambioContra("S")
                .build();

        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(usuarioCliente).getUserByEmailAndPass(Mockito.any(), Mockito.any());
        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmail(Mockito.any());

        LOGIN_ACTION rta = loginService.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.USER_BLOKED, rta);
    }


    @Test
    public void testValidaLoginUSER_NOT_FOUND(){
        Mockito.doReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT)).when(usuarioCliente).getUserByEmail(Mockito.any());

        LOGIN_ACTION rta = loginService.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.USER_NOT_FOUND, rta);
    }

}