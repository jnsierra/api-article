package co.com.ud.datos.controller;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.service.IdeaService;
import co.com.ud.utiles.dto.IdeaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/idea")
public class IdeaControler {

    private final IdeaService ideaService;
    private final ModelMapper mapper;

    @Autowired
    public IdeaControler(IdeaService ideaService, ModelMapper mapper) {
        this.ideaService = ideaService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaDto> save(IdeaDto idea){
        Optional<IdeaEntity> ideaEntity = ideaService.save(mapper.map(idea, IdeaEntity.class));
        if(ideaEntity.isPresent()){
            return new ResponseEntity<>( mapper.map(ideaEntity.get(), IdeaDto.class),   HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
