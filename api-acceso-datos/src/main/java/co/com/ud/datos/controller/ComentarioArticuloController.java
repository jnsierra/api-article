package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ComentarioArticuloEntity;
import co.com.ud.datos.service.ComentarioArticuloService;
import co.com.ud.utiles.dto.ComentarioArticuloDto;
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
@RequestMapping("/v.1/comentarioarticulo")
public class ComentarioArticuloController {

    private final ModelMapper map;
    private final ComentarioArticuloService comentarioArticuloService;

    @Autowired
    public ComentarioArticuloController(ModelMapper map, ComentarioArticuloService comentarioArticuloService) {
        this.map = map;
        this.comentarioArticuloService = comentarioArticuloService;
    }

    @PostMapping(value = "/" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioArticuloDto> save(@RequestBody ComentarioArticuloDto comentarioArticuloDto){
        Optional<ComentarioArticuloEntity> response = comentarioArticuloService.save(map.map(comentarioArticuloDto, ComentarioArticuloEntity.class));
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(map.map(response.get(), ComentarioArticuloDto.class),  HttpStatus.OK);
    }
}
