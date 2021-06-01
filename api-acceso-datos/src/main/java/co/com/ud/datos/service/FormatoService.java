package co.com.ud.datos.service;

import co.com.ud.datos.entity.FormatoEntity;
import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;

import java.util.List;
import java.util.Optional;

public interface FormatoService {

    Optional<FormatoEntity> save(FormatoEntity formato);

    List<FormatoEntity> findFormatoByFormatoAndEstadoAndArtId(Long idArt, String estado, TYPE_FORMATO_ARTICULO formato);

    List<FormatoEntity> findByIdArt(Long idArt);
}
