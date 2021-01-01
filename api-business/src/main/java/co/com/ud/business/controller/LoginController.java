package co.com.ud.business.controller;


import brave.Span;
import brave.Tracer;
import co.com.ud.business.service.LoginService;
import co.com.ud.business.service.TokenService;
import co.com.ud.business.service.impl.TokenServiceImpl;
import co.com.ud.utiles.dto.TokenDto;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final TokenService tokenService;
    private static final Logger logger = LogManager.getLogger(TokenServiceImpl.class);
    private final Tracer tracer;

    @Autowired
    public LoginController(LoginService loginService, TokenService tokenService, Tracer tracer) {
        this.loginService = loginService;
        this.tokenService = tokenService;
        this.tracer = tracer;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto usuarioDto, HttpServletRequest request) {
        tracer.currentSpan().tag("login.user", usuarioDto.getCorreo());

        LOGIN_ACTION respuestaLogin = loginService.validaLogin(usuarioDto.getCorreo(), usuarioDto.getContrasena());
        tracer.currentSpan().tag("login.action", respuestaLogin.toString());
        logger.info("LOGIN|{}|{}|{}",respuestaLogin.toString(), usuarioDto.getCorreo(), request.getRemoteAddr());

        if (LOGIN_ACTION.SUCCESS.equals(respuestaLogin) || LOGIN_ACTION.SUCCESS_CHANGE_PASSWORD.equals(respuestaLogin)) {
            //Genero el token en la aplicacion
            Optional<TokenDto> token = tokenService.generateTokenUser(usuarioDto.getCorreo());
            if (token.isPresent()) {
                token.get().setLoginAction(respuestaLogin);
                return new ResponseEntity<TokenDto>(token.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
