package co.com.ud.datos.service;

import co.com.ud.datos.entity.FormatoIdeaEntity;

import java.util.List;
import java.util.Optional;

public interface FormatoIdeaService {

    Optional<FormatoIdeaEntity> save(FormatoIdeaEntity formatoIdeaEntity);

    List<FormatoIdeaEntity> getFormatosByIdea(Long idIdea);

    List<FormatoIdeaEntity> getFormatosByIdeaAndTipoFormato(Long idIdea, String tipoFormato);
}
