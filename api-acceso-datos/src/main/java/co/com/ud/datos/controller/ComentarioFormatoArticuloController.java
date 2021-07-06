package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ComentarioFormatoArticuloEntity;
import co.com.ud.datos.service.ComentarioFormatoArticuloService;
import co.com.ud.utiles.dto.ComentarioFormatoArticuloDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/comentarioformatoarticulo")
public class ComentarioFormatoArticuloController {

    private final ComentarioFormatoArticuloService comentarioFormatoArticuloService;
    private final ModelMapper mapper;

    @Autowired
    public ComentarioFormatoArticuloController(ComentarioFormatoArticuloService comentarioFormatoArticuloService, ModelMapper mapper) {
        this.comentarioFormatoArticuloService = comentarioFormatoArticuloService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioFormatoArticuloDto> save(@RequestBody ComentarioFormatoArticuloDto comentarioFormatoArticuloDto){
        Optional<ComentarioFormatoArticuloEntity> response = comentarioFormatoArticuloService.save(mapper.map(comentarioFormatoArticuloDto, ComentarioFormatoArticuloEntity.class));
        if(Objects.isNull(response) || response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.mapper.map(response.get(), ComentarioFormatoArticuloDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/by/formato/{idFormato}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioFormatoArticuloDto[]> getByFormato(@PathVariable(name = "idFormato", required = false)Long idFormato){
        List<ComentarioFormatoArticuloEntity> response = comentarioFormatoArticuloService.getByFormato(idFormato);
        if(Objects.isNull(response) || (Objects.nonNull(response) && response.isEmpty()) ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.mapper.map(response,ComentarioFormatoArticuloDto[].class), HttpStatus.OK);
    }
}
