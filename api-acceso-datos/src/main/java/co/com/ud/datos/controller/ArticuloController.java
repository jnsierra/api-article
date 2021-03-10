package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.service.ArticuloService;
import co.com.ud.utiles.dto.ArticuloDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
