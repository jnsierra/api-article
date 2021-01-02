package co.com.ud.business.controller;

import brave.Tracer;
import brave.Tracing;
import co.com.ud.business.service.LoginService;
import co.com.ud.business.service.TokenService;
import co.com.ud.utiles.dto.TokenDto;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@SpringBootTest
public class LoginControllerTest {

    private LoginController loginController;
    @Mock
    private LoginService loginService;
    @Mock
    private TokenService tokenService;
    @Mock
    private Tracer tracer;
    @Mock
    private HttpServletRequest httpServletRequest;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.tracer = Tracing.newBuilder().build().tracer();
        this.loginController = new LoginController(loginService, tokenService, tracer);
    }
    @Test
    public void testLOGINSUCCESS(){

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .correo("jnsierrac@gmail.com")
                .contrasena("123456")
                .build();

        Assert.assertTrue(Boolean.TRUE);

        Mockito.doReturn(LOGIN_ACTION.SUCCESS).when(loginService).validaLogin(Mockito.any(), Mockito.any());
        Optional<TokenDto> token = Optional.of(TokenDto.builder()
                .loginAction(LOGIN_ACTION.SUCCESS)
                .time(3600)
                .mensaje("Login exitoso")
                .build());

        Mockito.doReturn(token).when(tokenService).generateTokenUser(Mockito.any());


        ResponseEntity<TokenDto> response = loginController.login(usuarioDto, httpServletRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginUNAUTHORIZED(){

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .correo("jnsierrac@gmail.com")
                .contrasena("123456")
                .build();

        Assert.assertTrue(Boolean.TRUE);

        Mockito.doReturn(LOGIN_ACTION.PASSWORD_INCORRECT).when(loginService).validaLogin(Mockito.any(), Mockito.any());

        ResponseEntity<TokenDto> response = loginController.login(usuarioDto, httpServletRequest);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}