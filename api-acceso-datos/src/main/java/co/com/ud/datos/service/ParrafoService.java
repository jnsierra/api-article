package co.com.ud.datos.service;

import co.com.ud.datos.entity.ParrafoEntity;

import java.util.List;
import java.util.Optional;

public interface ParrafoService {

    Optional<ParrafoEntity> save(ParrafoEntity parrafo);

    List<ParrafoEntity> getParrafoByArticulo(Long artId);

    Optional<Boolean> updateOrden(Long idParrafo, Long orden);

}
