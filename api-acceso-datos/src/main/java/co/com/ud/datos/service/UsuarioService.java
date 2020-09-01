package co.com.ud.datos.service;

import co.com.ud.datos.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioEntity save(UsuarioEntity usuario);

    List<UsuarioEntity> getAll();

    Optional<UsuarioEntity> getUserFindEmailAndPass(String email, String pass);

}
