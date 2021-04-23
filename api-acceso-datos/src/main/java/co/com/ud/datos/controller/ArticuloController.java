package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.service.ArticuloService;
import co.com.ud.utiles.dto.ArticuloDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;
    private final ModelMapper map;

    @Autowired
    public ArticuloController(ArticuloService articuloService, ModelMapper map) {
        this.articuloService = articuloService;
        this.map = map;
    }

    @GetMapping(value = "/idea/{id}/")
    public ResponseEntity<ArticuloDto> getByIdeaId(@PathVariable("id") Long idIdea){
        Optional<ArticuloEntity> articulo = articuloService.findByIdIdea(idIdea);
        if(articulo.isPresent()){
            return new ResponseEntity<>(map.map(articulo.get(), ArticuloDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloDto> save(@RequestBody ArticuloDto articuloDto){
        Optional<ArticuloEntity> response = articuloService.save(map.map(articuloDto, ArticuloEntity.class));
        if(response.isPresent()){
            return new ResponseEntity<>( map.map(response.get(), ArticuloDto.class) , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
