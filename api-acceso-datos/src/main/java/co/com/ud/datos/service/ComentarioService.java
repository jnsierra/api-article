package co.com.ud.datos.service;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {

    Optional<ComentarioEntity> save(ComentarioEntity comentario);

    List<ComentarioEntity> findByLlaveAndTipoComentario(Long llave, TYPE_COMMENTS type_comments);
}
