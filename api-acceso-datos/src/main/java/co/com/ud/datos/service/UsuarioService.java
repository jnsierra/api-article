package co.com.ud.datos.service;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.utiles.enumeracion.USER_STATE;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioEntity save(UsuarioEntity usuario);

    List<UsuarioEntity> getAll();

    Optional<UsuarioEntity> getUserFindEmailAndPass(String email, String pass);

    Optional<UsuarioEntity> getFiltersUniques(String email, String pass);

    Optional<Boolean> updateIntentosLoginUsuario(String email);

    Optional<UsuarioEntity> getUserById(Long id);

    Optional<Boolean> modifyEstadoTipoUsuario(Long id, USER_STATE estado, Long idTipoUsuario);

    List<UsuarioEntity> getUserByTipoUsuario(String tipoUsuario);
}
