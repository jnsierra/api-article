package co.com.ud.business.service;

import co.com.ud.utiles.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioDto> getUsuarioByCorreo(String correo);

    List<UsuarioDto> getUsers();

    Optional<UsuarioDto> getUserById(Long id);

    Optional<UsuarioDto> save(UsuarioDto usuario);
}
