package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.FormatoIdeaEntity;
import co.com.ud.datos.repository.IFormatoIdeaRepository;
import co.com.ud.datos.service.FormatoIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormatoIdeaServiceImpl implements FormatoIdeaService {

    private final IFormatoIdeaRepository formatoIdeaRepository;

    @Autowired
    public FormatoIdeaServiceImpl(IFormatoIdeaRepository formatoIdeaRepository) {
        this.formatoIdeaRepository = formatoIdeaRepository;
    }

    @Override
    public Optional<FormatoIdeaEntity> save(FormatoIdeaEntity formatoIdeaEntity) {
        FormatoIdeaEntity response = formatoIdeaRepository.save(formatoIdeaEntity);
        return Optional.of(response);
    }

    @Override
    public List<FormatoIdeaEntity> getFormatosByIdea(Long idIdea) {
        return formatoIdeaRepository.getFormatosByIdea(idIdea);
    }

    @Override
    public List<FormatoIdeaEntity> getFormatosByIdeaAndTipoFormato(Long idIdea, String tipoFormato) {
        return formatoIdeaRepository.getFormatosByIdeaAndTipoFormato(idIdea, tipoFormato);
    }
}
