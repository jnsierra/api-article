package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.MailCliente;
import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.EmailDto;
import co.com.ud.utiles.dto.TipoUsuarioDto;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.USER_STATE;
import co.com.ud.utiles.service.PasswordUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioCliente usuarioCliente;
    private final MailCliente mailCliente;

    @Autowired
    public UsuarioServiceImpl(UsuarioCliente usuarioCliente, MailCliente mailCliente) {
        this.usuarioCliente = usuarioCliente;
        this.mailCliente = mailCliente;
    }

    @Override
    public Optional<UsuarioDto> getUsuarioByCorreo(String correo) {
        ResponseEntity<UsuarioDto[]> response = usuarioCliente.getUserByEmail(correo);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody()[0]);
        }
        return Optional.empty();
    }

    @Override
    public List<UsuarioDto> getUsers() {
        ResponseEntity<UsuarioDto[]> response = usuarioCliente.get();
        if( HttpStatus.OK.equals(response.getStatusCode()) && response.getBody().length != 0){
            return Arrays.asList(response.getBody());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<UsuarioDto> getUserById(Long id) {
        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserById(id);
        if( HttpStatus.OK.equals(response.getStatusCode()) ){
            return Optional.of( response.getBody() );
        }
        return Optional.empty();
    }

    @Override
    public Optional<UsuarioDto> save(UsuarioDto usuario) {
        usuario.setIntentos(0);
        usuario.setEstado(USER_STATE.INACTIVO);
        //Por default el tipo de usuario es ALUMNO
        usuario.setTipoUsuario(TipoUsuarioDto.builder().id(3L).build());
        ResponseEntity<UsuarioDto> response = usuarioCliente.save(usuario);
        if(HttpStatus.CREATED.equals(response.getStatusCode())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    @Override
    public void updateIntentosLoginUser(String correo) {
        usuarioCliente.updateIntentosUser(correo);
    }

    @Override
    public Boolean recuperarContrasenia(String token, String correo) {
        //Valido Si el usuario existe
        ResponseEntity<UsuarioDto[]> responseValidate = usuarioCliente.getUserByEmail(correo);
        if(Objects.isNull(responseValidate) || (Objects.nonNull(responseValidate) && !HttpStatus.OK.equals(responseValidate.getStatusCode()) )  ){
            return Boolean.FALSE;
        }
        //Genero contraseña aletoria
        String password = PasswordUtiles.builder().build().generateRandomPassword(10);
        ResponseEntity<Boolean> response = mailCliente.enviarMail(token, EmailDto.builder()
                .subject("RECUPERAR CONTRASEÑA")
                .to(correo)
                .text("Clave temporal de acceso al sistema: " + password)
                .build());
        if(Objects.nonNull(response) && HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody()) && response.getBody()){
            //Actualizo la contraseña y
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}