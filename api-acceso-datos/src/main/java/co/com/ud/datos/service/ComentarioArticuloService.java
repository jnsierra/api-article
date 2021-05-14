package co.com.ud.datos.service;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;

import java.util.List;
import java.util.Optional;

public interface ComentarioArticuloService {

    Optional<ComentarioArticuloEntity> save(ComentarioArticuloEntity comentario);

    List<ComentarioArticuloEntity> findByTypeAndArt(TYPE_COMMENTS_ARTICLE type, Long idArt);
}
