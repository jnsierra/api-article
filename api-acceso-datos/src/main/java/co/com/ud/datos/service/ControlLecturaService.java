package co.com.ud.datos.service;

import co.com.ud.datos.entity.ControlLecturaEntity;

import java.util.List;
import java.util.Optional;

public interface ControlLecturaService {

    Optional<ControlLecturaEntity> save(ControlLecturaEntity controlLecturaEntity);

    List<ControlLecturaEntity> getByIdArticulo(Long idArt);

}
