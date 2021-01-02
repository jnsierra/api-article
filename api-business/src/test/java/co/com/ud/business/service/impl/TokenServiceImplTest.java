package co.com.ud.business.service.impl;

import brave.Response;
import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.utiles.dto.TokenDto;
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

import java.util.Optional;

@SpringBootTest
public class TokenServiceImplTest {
    @Mock
    private UsuarioCliente usuarioCliente;

    private TokenServiceImpl tokenService;

    private String secret;

    @Before
    public void setUp(){
        this.secret = "1234165479813";
        MockitoAnnotations.initMocks(this);
        this.tokenService = new TokenServiceImpl(usuarioCliente, secret);
    }

    @Test
    public void generateTokenUserSUCCESS(){
        UsuarioDto usuarioDto = UsuarioDto.builder().id(0L).build();
        Mockito.doReturn(new ResponseEntity<>(usuarioDto, HttpStatus.OK)).when(usuarioCliente).getUserByEmailAndPass(Mockito.any(), Mockito.any());

        Optional<TokenDto> rta = tokenService.generateTokenUser("jnsierrac@gmail.com");
        Assert.assertNotNull(rta);
        Assert.assertNotNull(rta.get().getToken());
    }

    @Test
    public void generateTokenUserNOT_FOUND_USER(){
        Mockito.doReturn(new ResponseEntity<>( HttpStatus.NO_CONTENT)).when(usuarioCliente).getUserByEmailAndPass(Mockito.any(), Mockito.any());
        Optional<TokenDto> rta = tokenService.generateTokenUser("jnsierrac@gmail.com");
        Assert.assertNotNull(rta);
        Assert.assertFalse(rta.isPresent());
    }

}