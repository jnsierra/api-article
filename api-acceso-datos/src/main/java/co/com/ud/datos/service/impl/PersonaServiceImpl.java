package co.com.ud.datos.service.impl;

import co.com.ud.datos.entity.PersonaEntity;
import co.com.ud.datos.repository.IPersonaRepository;
import co.com.ud.datos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final IPersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaEntity save(PersonaEntity persona) {
        return personaRepository.save(persona);
    }
}