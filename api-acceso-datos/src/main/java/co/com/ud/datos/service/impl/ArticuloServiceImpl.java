package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.repository.IArticuloRepository;
import co.com.ud.datos.service.ArticuloService;
import co.com.ud.utiles.dto.ArticuloDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final IArticuloRepository articuloRepository;
    private final ModelMapper mapper;

    @Autowired
    public ArticuloServiceImpl(IArticuloRepository articuloRepository, ModelMapper mapper) {
        this.articuloRepository = articuloRepository;
        this.mapper = mapper;
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

    @Override
    public Optional<ArticuloEntity> updateArticulo(ArticuloDto articuloDto) {
        Optional<ArticuloEntity> entity = articuloRepository.findById(articuloDto.getId());
        if(entity.isPresent()){
            Integer update = articuloRepository.updateArticulo(articuloDto.getId()
                    , articuloDto.getResumen_ingles()
                    , articuloDto.getResumen()
                    , articuloDto.getTitulo()
                    , articuloDto.getIntroduccion()
                    , articuloDto.getConclusion());
            if(update > 0){
                return articuloRepository.findById(articuloDto.getId());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArticuloEntity> updateEstadoArt(Long id, String estado) {
        Optional<ArticuloEntity> entity = articuloRepository.findById(id);
        if(entity.isPresent()){
            entity.get().setEstado(estado);
            ArticuloEntity art = articuloRepository.save(entity.get());
            return Optional.of(art);
        }
        return Optional.empty();
    }

    @Override
    public List<ArticuloEntity> getArticulosByTutorAndEstado(Long idTutor, String estado) {
        return articuloRepository.getArticulosByTutorAndEstado(idTutor, estado);
    }

}
