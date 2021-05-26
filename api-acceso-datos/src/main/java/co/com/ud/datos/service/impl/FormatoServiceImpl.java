package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.FormatoEntity;
import co.com.ud.datos.repository.IFormatoRepository;
import co.com.ud.datos.service.FormatoService;
import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormatoServiceImpl implements FormatoService {

    private final IFormatoRepository formatoRepository;

    @Autowired
    public FormatoServiceImpl(IFormatoRepository formatoRepository) {
        this.formatoRepository = formatoRepository;
    }

    @Override
    public Optional<FormatoEntity> save(FormatoEntity formato) {
        FormatoEntity entity = formatoRepository.save(formato);
        return Optional.of(entity);
    }

    @Override
    public List<FormatoEntity> findFormatoByFormatoAndEstadoAndArtId(Long idArt, String estado, TYPE_FORMATO_ARTICULO formato) {
        return null;
    }
}
