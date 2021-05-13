package co.com.ud.datos.service;

import co.com.ud.datos.entity.ComentarioArticuloEntity;

import java.util.Optional;

public interface ComentarioArticuloService {

    Optional<ComentarioArticuloEntity> save(ComentarioArticuloEntity comentario);
}
