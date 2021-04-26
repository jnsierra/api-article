package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.ControlLecturaEntity;
import co.com.ud.datos.repository.IControlLecturaRepository;
import co.com.ud.datos.service.ControlLecturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControlLecturaServiceImpl implements ControlLecturaService {

    private final IControlLecturaRepository controlLecturaRepository;

    @Autowired
    public ControlLecturaServiceImpl(IControlLecturaRepository controlLecturaRepository) {
        this.controlLecturaRepository = controlLecturaRepository;
    }

    @Override
    public Optional<ControlLecturaEntity> save(ControlLecturaEntity controlLecturaEntity) {
        ControlLecturaEntity controlLectura = this.controlLecturaRepository.save(controlLecturaEntity);
        return Optional.of(controlLectura);
    }

    @Override
    public List<ControlLecturaEntity> getByIdArticulo(Long idArt) {
        return controlLecturaRepository.getByIdArticulo(idArt);
    }
}
