package co.com.ud.datos.service;

import co.com.ud.datos.entity.ArticuloEntity;

import java.util.Optional;

public interface ArticuloService {

    Optional<ArticuloEntity> findByIdIdea(Long idIdea);

}
