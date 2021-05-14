package co.com.ud.datos.service;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.utiles.dto.ArticuloDto;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {

    Optional<ArticuloEntity> findByIdIdea(Long idIdea);

    Optional<ArticuloEntity> save(ArticuloEntity articuloEntity);

    List<ArticuloEntity> getArticulosByUser(Long idUser);

    Optional<ArticuloEntity> getById(Long id);

    Optional<ArticuloEntity> updateArticulo(ArticuloDto articuloDto);

    Optional<ArticuloEntity> updateEstadoArt(Long id, String estado);

    List<ArticuloEntity> getArticulosByTutorAndEstado(Long idTutor, String estado);

}
