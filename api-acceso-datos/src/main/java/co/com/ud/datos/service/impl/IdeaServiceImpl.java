package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.repository.IIdeaRepository;
import co.com.ud.datos.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final IIdeaRepository ideaRepository;

    @Autowired
    public IdeaServiceImpl(IIdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @Override
    public Optional<IdeaEntity> findById(Long id) {
        return ideaRepository.findById(id);
    }

    @Override
    public Optional<IdeaEntity> save(IdeaEntity idea) {
        IdeaEntity ideaEntity = ideaRepository.save(idea);
        return Optional.of(ideaEntity);
    }

    @Override
    public List<IdeaEntity> findByUsuarioId(Long id) {
        return ideaRepository.findByUsuarioId(id);
    }

    @Override
    public List<IdeaEntity> findByProfesorIdAndEstado(Long idProfesor, String estado) {
        return ideaRepository.findByProfesorIdAndEstado(idProfesor, estado);
    }

    @Override
    public Optional<Boolean> modificarIdProfAutorizaAndEstadoAndFechaAutoriza(Long idIdea, Long idProf, String estado) {
        Integer registro = ideaRepository.modificarIdProfAutorizaAndEstadoAndFechaAutoriza(idIdea, idProf, estado, new Date());
        if(registro > 0){
            return Optional.of(Boolean.TRUE);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> modificaIdea(IdeaEntity idea) {
        Integer response = ideaRepository.modificarIdea(idea.getId(),idea.getTitulo(),idea.getContenido(), idea.getEstado(), idea.getId_profesor());
        return response > 0 ? Optional.of(Boolean.TRUE) : Optional.of(Boolean.FALSE) ;
    }

    @Override
    public Optional<Boolean> modificarEstado(Long idIdea, String estado) {
        Integer response = ideaRepository.modificarEstado(idIdea, estado);
        return response > 0 ? Optional.of(Boolean.TRUE) : Optional.of(Boolean.FALSE) ;
    }

}