package co.com.ud.datos.service;

import co.com.ud.datos.entity.FormatoIdeaEntity;

import java.util.Optional;

public interface FormatoIdeaService {

    Optional<FormatoIdeaEntity> save(FormatoIdeaEntity formatoIdeaEntity);
}
