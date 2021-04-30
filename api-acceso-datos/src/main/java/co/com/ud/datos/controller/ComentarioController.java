package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ComentarioEntity;
import co.com.ud.datos.service.ComentarioService;
import co.com.ud.utiles.dto.ComentarioDto;
import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
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

    @GetMapping(value = "/by/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComentarioDto[]> getComentariosByLLaveAndTypeComments(@RequestParam(name = "llave", required = false) Long llave,
                                                                                @RequestParam(name = "type_comments", required = false)TYPE_COMMENTS type){
        List<ComentarioEntity> list = comentarioService.findByLlaveAndTipoComentario(llave, type);
        if(Objects.nonNull(list) && !list.isEmpty()){
            return new ResponseEntity<>(modelMapper.map(list, ComentarioDto[].class ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/ubicacion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateUbicacionComentario(@RequestBody ComentarioDto comentarioDto){
        Optional<Boolean> response = comentarioService.updateComentarioUbicacion(comentarioDto.getId(), comentarioDto.getUbicacion());
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}