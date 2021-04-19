package co.com.ud.business.service;

import co.com.ud.utiles.dto.IdeaDto;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

    List<IdeaDto> findIdeasByUsuario(String token, Long idUsuario);

    List<IdeaDto> findByProfesorIdAndEstado(String token, Long idProfesor, String estado);

    Optional<IdeaDto> findById(String token, Long idIdea);

    Optional<Boolean> updateStatus(String token, Long idIdea, String status);
}
