package co.com.ud.datos.service;

import co.com.ud.datos.entity.ComentarioFormatoArticuloEntity;

import java.util.Optional;

public interface ComentarioFormatoArticuloService {

    Optional<ComentarioFormatoArticuloEntity> save(ComentarioFormatoArticuloEntity comentarioFormatoArticulo);
}
