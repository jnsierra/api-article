package co.com.ud.business.controller;


import brave.Tracer;
import co.com.ud.business.service.LoginService;
import co.com.ud.business.service.TokenService;
import co.com.ud.business.service.impl.TokenServiceImpl;
import co.com.ud.utiles.dto.TokenDto;
import co.com.ud.utiles.dto.UsuarioDto;
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
        tracer.currentSpan().tag("login.actual", usuarioDto.getCorreo());
        Boolean validaLogin = loginService.validaLogin(usuarioDto.getCorreo(), usuarioDto.getContrasena());
        if (Boolean.TRUE.equals(validaLogin)) {
            //Genero el token en la aplicacion
            Optional<TokenDto> token = tokenService.generateTokenUser(usuarioDto.getCorreo());
            if (token.isPresent()) {
                logger.info("{}|{}|{}","LOGINSUCCESS",usuarioDto.getCorreo(),request.getRemoteAddr());
                return new ResponseEntity<TokenDto>(token.get(), HttpStatus.OK);
            }
        }
        logger.info("{}|{}|{}","LOGINFAILED",usuarioDto.getCorreo(),request.getRemoteAddr());
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
