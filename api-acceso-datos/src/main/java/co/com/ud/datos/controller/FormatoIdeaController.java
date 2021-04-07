package co.com.ud.datos.controller;

import co.com.ud.datos.entity.FormatoIdeaEntity;
import co.com.ud.datos.service.FormatoIdeaService;
import co.com.ud.utiles.dto.FormatoIdeaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/formatoidea")
public class FormatoIdeaController {

    private final FormatoIdeaService formatoIdeaService;
    private final ModelMapper modelMapper;

    @Autowired
    public FormatoIdeaController(FormatoIdeaService formatoIdeaService, ModelMapper modelMapper) {
        this.formatoIdeaService = formatoIdeaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormatoIdeaDto> save(@RequestBody FormatoIdeaDto formatoIdeaDto){
        Optional<FormatoIdeaEntity> formatoIdea = formatoIdeaService.save(modelMapper.map(formatoIdeaDto, FormatoIdeaEntity.class));
        if(formatoIdea.isPresent()){
            return new ResponseEntity<>(modelMapper.map(formatoIdea.get(), FormatoIdeaDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
