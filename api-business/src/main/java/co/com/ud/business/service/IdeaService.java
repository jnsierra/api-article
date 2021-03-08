package co.com.ud.business.service;

import co.com.ud.utiles.dto.IdeaDto;

import java.util.List;

public interface IdeaService {

    List<IdeaDto> findIdeasByUsuario(Long idUsuario);

    List<IdeaDto> findByProfesorIdAndEstado(Long idProfesor, String estado);
}
