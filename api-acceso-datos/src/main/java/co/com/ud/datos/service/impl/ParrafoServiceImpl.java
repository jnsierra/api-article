package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ParrafoEntity;
import co.com.ud.datos.repository.IParrafoRepository;
import co.com.ud.datos.service.ParrafoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParrafoServiceImpl implements ParrafoService {

    private final IParrafoRepository parrafoRepository;

    @Autowired
    public ParrafoServiceImpl(IParrafoRepository parrafoRepository) {
        this.parrafoRepository = parrafoRepository;
    }

    @Override
    public Optional<ParrafoEntity> save(ParrafoEntity parrafo) {
        ParrafoEntity entity = parrafoRepository.save(parrafo);
        return Optional.of(entity);
    }

    @Override
    public List<ParrafoEntity> getParrafoByArticulo(Long artId) {
        return parrafoRepository.getParrafoByArticulo(artId);
    }

    @Override
    public Optional<Boolean> updateOrden(Long idParrafo, Long orden) {
        Integer modifica = parrafoRepository.modificarOrden(idParrafo, orden);
        return modifica > 0 ? Optional.of(Boolean.TRUE) : Optional.of(Boolean.FALSE);
    }

}
