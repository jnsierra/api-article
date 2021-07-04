package co.com.ud.business.controller;

import co.com.ud.business.service.ArticulosService;
import co.com.ud.business.service.IdeaService;
import co.com.ud.utiles.dto.CountStateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/estadisticas")
public class EstadisticasController {



    private final IdeaService ideaService;
    private final ArticulosService articulosService;

    @Autowired
    public EstadisticasController(IdeaService ideaService, ArticulosService articulosService) {
        this.ideaService = ideaService;
        this.articulosService = articulosService;
    }

    @RequestMapping(value = "/ideas/estado/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountStateDto[]> obtenerIdeasByEstado(@RequestHeader("Authorization")String autenticacion){
        Optional<CountStateDto[]> response = ideaService.getIdeasNumIdeasByEstado(autenticacion);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/articulo/estado/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountStateDto[]> obtenerArticuloByEstado(@RequestHeader("Authorization")String autenticacion){
        Optional<CountStateDto[]> response = articulosService.getNumArticulosByEstado(autenticacion);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}