package co.com.ud.datos.service;

import co.com.ud.datos.entity.ComentarioFormatoArticuloEntity;

import java.util.List;
import java.util.Optional;

public interface ComentarioFormatoArticuloService {

    Optional<ComentarioFormatoArticuloEntity> save(ComentarioFormatoArticuloEntity comentarioFormatoArticulo);

    List<ComentarioFormatoArticuloEntity> getByFormato(Long idFormato);
}
