package co.com.ud.datos.service;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.utiles.enumeracion.TYPE_PROFESOR;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

    Optional<IdeaEntity> findById(Long id);

    Optional<IdeaEntity> save(IdeaEntity idea);

    List<IdeaEntity> findByUsuarioId(Long id);

    List<IdeaEntity> findByProfesorIdAndEstado(Long idProfesor, String estado, TYPE_PROFESOR typeProfesor);

    List<IdeaEntity> findByEstado(String estadoIdea );

    Optional<Boolean> modificarIdProfAutorizaAndEstadoAndFechaAutoriza(Long idIdea, Long idProf, String estado);

    Optional<Boolean> modificaIdea(IdeaEntity idea);

    Optional<Boolean> modificarEstado(Long idIdea, String estado);

    Optional<Boolean> modificarJurado(Long idIdea, Long idJurado);
}
