package co.com.ud.business.service;

import co.com.ud.utiles.dto.IdeaDto;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

    List<IdeaDto> findIdeasByUsuario(Long idUsuario);
}
