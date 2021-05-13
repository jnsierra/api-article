package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.datos.repository.IComentarioArticuloRepository;
import co.com.ud.datos.service.ComentarioArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
