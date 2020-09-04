package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.LoginService;
import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UsuarioCliente usuarioCliente;

    @Autowired
    public LoginServiceImpl(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    @Override
    public Boolean validaLogin(String correo, String pass) {
        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserByEmailAndPass(correo, pass);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
