package co.com.ud.datos.service;

import co.com.ud.datos.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    UsuarioEntity save(UsuarioEntity usuario);

    List<UsuarioEntity> getAll();

}
