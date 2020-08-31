package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.repository.ITipoUsuarioRepository;
import co.com.ud.datos.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final ITipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioServiceImpl(ITipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public TipoUsuarioEntity save(TipoUsuarioEntity tipoUsuarioEntity) {
        return tipoUsuarioRepository.save(tipoUsuarioEntity);
    }
    
}
