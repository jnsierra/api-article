package co.com.ud.datos.service;

import co.com.ud.datos.entity.ComentarioEntity;

import java.util.Optional;

public interface ComentarioService {

    Optional<ComentarioEntity> save(ComentarioEntity comentario);
}
