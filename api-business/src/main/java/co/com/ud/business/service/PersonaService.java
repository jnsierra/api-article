package co.com.ud.business.service;

import co.com.ud.utiles.dto.PersonaDto;

import java.util.Optional;

public interface PersonaService {

    Optional<PersonaDto> save(PersonaDto persona);
}
