package co.com.ud.datos.controller;

import co.com.ud.datos.entity.PersonaEntity;
import co.com.ud.datos.service.PersonaService;
import co.com.ud.utiles.dto.PersonaDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/personas")
public class PersonaController {

    private final ModelMapper mapper;
    private final PersonaService personaService;

    public PersonaController(ModelMapper mapper, PersonaService personaService) {
        this.mapper = mapper;
        this.personaService = personaService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonaDto> save(@RequestBody(required = true) PersonaDto personaDto) {
        PersonaEntity personaEntity = personaService.save(mapper.map(personaDto, PersonaEntity.class));
        return new ResponseEntity<>(mapper.map(personaEntity, PersonaDto.class), HttpStatus.CREATED);
    }
}
