package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.datos.repository.IComentarioArticuloRepository;
import co.com.ud.datos.service.ComentarioArticuloService;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioArticuloServiceImpl implements ComentarioArticuloService {

    private final IComentarioArticuloRepository comentarioArticuloRepository;

    @Autowired
    public ComentarioArticuloServiceImpl(IComentarioArticuloRepository comentarioArticuloRepository) {
        this.comentarioArticuloRepository = comentarioArticuloRepository;
    }

    @Override
    public Optional<ComentarioArticuloEntity> save(ComentarioArticuloEntity comentario) {
        ComentarioArticuloEntity entity = comentarioArticuloRepository.save(comentario);
        return Optional.of(entity);
    }

    @Override
    public List<ComentarioArticuloEntity> findByTypeAndArt(TYPE_COMMENTS_ARTICLE type, Long idArt) {
        return comentarioArticuloRepository.findByTypeComentarioArtAndArticulo(type, idArt);
    }

    @Override
    public List<ComentarioArticuloEntity> findByArtId(Long idArt) {
        return comentarioArticuloRepository.findByArticulo(idArt);
    }

    @Override
    public Optional<Boolean> updateRespuestaComentario(Long id, String respuesta) {
        Optional<ComentarioArticuloEntity> comentario = comentarioArticuloRepository.findById(id);
        if(comentario.isPresent()){
            comentario.get().setRespuestaComentario(respuesta);
            comentarioArticuloRepository.save(comentario.get());
            return Optional.of(Boolean.TRUE);
        }
        return Optional.of(Boolean.FALSE);
    }
}
