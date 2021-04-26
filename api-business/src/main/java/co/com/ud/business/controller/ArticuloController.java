package co.com.ud.business.controller;

import co.com.ud.business.service.ArticulosService;
import co.com.ud.utiles.dto.ArticuloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/articulos")
public class ArticuloController {

    private final ArticulosService articulosService;

    @Autowired
    public ArticuloController(ArticulosService articulosService) {
        this.articulosService = articulosService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ArticuloDto> save(@RequestHeader("Authorization")String autenticacion, @RequestBody ArticuloDto articuloDto){
        Optional<ArticuloDto> response = articulosService.save(autenticacion, articuloDto);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}