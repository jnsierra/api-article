package co.com.ud.business.service;

import co.com.ud.utiles.dto.UsuarioDto;

import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDto> getUsuarioByCorreo(String correo);
}
