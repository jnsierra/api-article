package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.datos.service.ComentarioService;
import co.com.ud.utiles.dto.ComentarioDto;
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
@RequestMapping("/v.1/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final ModelMapper modelMapper;

    @Autowired
    public ComentarioController(ComentarioService comentarioService, ModelMapper modelMapper) {
        this.comentarioService = comentarioService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioDto> save(@RequestBody ComentarioDto comentarioDto){
        Optional<ComentarioEntity> comentario = comentarioService.save(modelMapper.map(comentarioDto, ComentarioEntity.class));
        if(comentario.isPresent()){
            return new ResponseEntity<>(modelMapper.map(comentario.get(), ComentarioDto.class),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
