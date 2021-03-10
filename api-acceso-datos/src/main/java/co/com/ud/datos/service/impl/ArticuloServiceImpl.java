package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.repository.IArticuloRepository;
import co.com.ud.datos.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final IArticuloRepository articuloRepository;

    @Autowired
    public ArticuloServiceImpl(IArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public Optional<ArticuloEntity> findByIdIdea(Long idIdea) {
        return articuloRepository.findByIdIdea(idIdea);
    }

}
