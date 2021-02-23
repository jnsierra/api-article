package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.PersonaCliente;
import co.com.ud.business.service.PersonaService;
import co.com.ud.utiles.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaCliente personaCliente;

    @Autowired
    public PersonaServiceImpl(PersonaCliente personaCliente) {
        this.personaCliente = personaCliente;
    }

    @Override
    public Optional<PersonaDto> save(PersonaDto persona) {
        ResponseEntity<PersonaDto> response = personaCliente.save(persona);
        if(HttpStatus.CREATED.equals(response.getStatusCode())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }
}
