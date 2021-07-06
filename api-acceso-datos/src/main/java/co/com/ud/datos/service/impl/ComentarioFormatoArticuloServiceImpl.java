package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ComentarioFormatoArticuloEntity;
import co.com.ud.datos.repository.IComentarioFormatoArticuloRepository;
import co.com.ud.datos.service.ComentarioFormatoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioFormatoArticuloServiceImpl implements ComentarioFormatoArticuloService {

    private final IComentarioFormatoArticuloRepository comentarioFormatoArticuloRepository;

    @Autowired
    public ComentarioFormatoArticuloServiceImpl(IComentarioFormatoArticuloRepository comentarioFormatoArticuloRepository) {
        this.comentarioFormatoArticuloRepository = comentarioFormatoArticuloRepository;
    }

    @Override
    public Optional<ComentarioFormatoArticuloEntity> save(ComentarioFormatoArticuloEntity comentarioFormatoArticulo) {
        ComentarioFormatoArticuloEntity response = comentarioFormatoArticuloRepository.save(comentarioFormatoArticulo);
        return Optional.of(response);
    }

    @Override
    public List<ComentarioFormatoArticuloEntity> getByFormato(Long idFormato) {
        return comentarioFormatoArticuloRepository.getByFormato(idFormato);
    }
}
