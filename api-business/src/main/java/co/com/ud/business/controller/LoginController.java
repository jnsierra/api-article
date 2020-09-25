package co.com.ud.business.controller;


import co.com.ud.business.service.LoginService;
import co.com.ud.business.service.TokenService;
import co.com.ud.utiles.dto.TokenDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final TokenService tokenService;

    @Autowired
    public LoginController(LoginService loginService, TokenService tokenService) {
        this.loginService = loginService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto usuarioDto) {
        Boolean validaLogin = loginService.validaLogin(usuarioDto.getCorreo(), usuarioDto.getContrasena());
        if (Boolean.TRUE.equals(validaLogin)) {
            //Genero el token en la aplicacion
            Optional<TokenDto> token = tokenService.generateTokenUser(usuarioDto.getCorreo());
            if (token.isPresent()) {
                return new ResponseEntity<TokenDto>(token.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
