package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.LoginService;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import co.com.ud.utiles.enumeracion.USER_STATE;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UsuarioCliente usuarioCliente;
    @Getter
    @Setter
    private UsuarioDto usuarioDto;

    @Autowired
    public LoginServiceImpl(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    @Override
    public LOGIN_ACTION validaLogin(String correo, String pass) {
        if (validateUser(correo)) {
            if (validateStateUser()) {
                if (validatePassword(correo, pass)) {
                    if(validateChagePass()){
                        return LOGIN_ACTION.SUCCESS_CHANGE_PASSWORD;
                    }
                    return LOGIN_ACTION.SUCCESS;
                }
                return LOGIN_ACTION.PASSWORD_INCORRECT;
            }
            return LOGIN_ACTION.USER_BLOKED;
        }
        return LOGIN_ACTION.USER_NOT_FOUND;
    }

    /**
     * Metodo con el cual se valida si el correo digitado existe o no, y en el caso de existir guarda el usuario en una
     * variable en la clase para realizar las demas validaciones sin llamar de nuevo el servicio
     *
     * @param correo
     * @return
     */
    private Boolean validateUser(String correo) {
        ResponseEntity<UsuarioDto[]> response = usuarioCliente.getUserByEmail(correo);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            setUsuarioDto(response.getBody()[0]);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validateStateUser() {
        if(USER_STATE.ACTIVO.equals(usuarioDto.getEstado())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validatePassword(String correo, String pass) {
        ResponseEntity<UsuarioDto[]> response = usuarioCliente.getUserByEmailAndPass(correo, pass);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean validateChagePass() {
        if("S".equals(getUsuarioDto().getCambioContra())){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}