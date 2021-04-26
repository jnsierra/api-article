package co.com.ud.datos.service;

import co.com.ud.datos.entity.ArticuloEntity;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {

    Optional<ArticuloEntity> findByIdIdea(Long idIdea);

    Optional<ArticuloEntity> save(ArticuloEntity articuloEntity);

    List<ArticuloEntity> getArticulosByUser(Long idUser);

    Optional<ArticuloEntity> getById(Long id);

}
