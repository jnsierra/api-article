package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.TipoUsuarioDto;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioCliente usuarioCliente;

    @Autowired
    public UsuarioServiceImpl(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    @Override
    public Optional<UsuarioDto> getUsuarioByCorreo(String correo) {
        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserByEmail(correo);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return Optional.of(response.getBody());
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

}