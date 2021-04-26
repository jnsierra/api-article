package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.repository.IArticuloRepository;
import co.com.ud.datos.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Optional<ArticuloEntity> save(ArticuloEntity articuloEntity) {
        ArticuloEntity response = articuloRepository.save(articuloEntity);
        return Optional.of(response);
    }

    @Override
    public List<ArticuloEntity> getArticulosByUser(Long idUser) {
        return articuloRepository.getArticulosByUser(idUser);
    }

    @Override
    public Optional<ArticuloEntity> getById(Long id) {
        return articuloRepository.findById(id);
    }

}
