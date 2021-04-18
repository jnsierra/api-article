package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.datos.repository.IComentarioRepository;
import co.com.ud.datos.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final IComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioServiceImpl(IComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public Optional<ComentarioEntity> save(ComentarioEntity comentario) {
        return Optional.of(this.comentarioRepository.save(comentario));
    }
}
