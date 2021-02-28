package co.com.ud.datos.service;

import co.com.ud.datos.entity.IdeaEntity;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

    Optional<IdeaEntity> save(IdeaEntity idea);

    List<IdeaEntity> findByUsuarioId(Long id);
}
