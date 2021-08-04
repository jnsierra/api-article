package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ArticuloEntity;
import co.com.ud.datos.service.ArticuloService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.CountStateDto;
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
@RequestMapping("/v.1/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;
    private final ModelMapper map;

    @Autowired
    public ArticuloController(ArticuloService articuloService, ModelMapper map) {
        this.articuloService = articuloService;
        this.map = map;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloDto> save(@RequestBody ArticuloDto articuloDto){
        Optional<ArticuloEntity> response = articuloService.save(map.map(articuloDto, ArticuloEntity.class));
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( map.map(response.get(), ArticuloDto.class) , HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloDto> updateArticulo(@RequestBody ArticuloDto articuloDto){
        Optional<ArticuloEntity> articulo = articuloService.updateArticulo(articuloDto);
        if(articulo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(map.map(articulo.get(), ArticuloDto.class) ,HttpStatus.OK);

    }
    @PutMapping(value = "/cambiarestado/")
    public ResponseEntity<ArticuloDto> updateEstadoArticulo(@RequestParam("idArticulo") Long idArt,@RequestParam("estado") String estado){
        Optional<ArticuloEntity> articulos = articuloService.updateEstadoArt(idArt, estado);
        if(articulos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(map.map(articulos.get(), ArticuloDto.class), HttpStatus.OK);
    }
    @PutMapping(value = "/cambiarUbicFormato/")
    public ResponseEntity<ArticuloDto> updateUbicacionFormato(@RequestBody ArticuloDto articuloDto){
        Optional<ArticuloEntity> articulos = articuloService.updateUbicacionFormato(articuloDto.getId(), articuloDto.getUbicacion_formato());
        if(articulos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(map.map(articulos.get(), ArticuloDto.class), HttpStatus.OK);
    }

    @PutMapping(value = "/cambiarUbicCartaPubliacion/")
    public ResponseEntity<ArticuloDto> updateUbicacionCartaPublicacion(@RequestBody ArticuloDto articuloDto){
        Optional<ArticuloEntity> articulos = articuloService.updateUbicacionCartaPublicacion(articuloDto.getId(), articuloDto.getUbicacion_formato());
        if(articulos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(map.map(articulos.get(), ArticuloDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/idea/user/{idUser}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloDto[]> getArticulosByUser(@PathVariable("idUser") Long idUser){
        List<ArticuloEntity> response = articuloService.getArticulosByUser(idUser);
        if(!response.isEmpty()){
            return new ResponseEntity<>(map.map(response,ArticuloDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/by/{idArt}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticuloDto> getById(@PathVariable Long idArt){
        Optional<ArticuloEntity> articulo = articuloService.getById(idArt);
        if (articulo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(map.map(articulo.get(), ArticuloDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/idea/{id}/")
    public ResponseEntity<ArticuloDto> getByIdeaId(@PathVariable("id") Long idIdea){
        Optional<ArticuloEntity> articulo = articuloService.findByIdIdea(idIdea);
        if(articulo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ArticuloDto responseObj = map.map(articulo.get(), ArticuloDto.class);
        return new ResponseEntity<>(responseObj, HttpStatus.OK);
    }

    @GetMapping(value = "/profesor/{idTutor}/{estado}/")
    public ResponseEntity<ArticuloDto[]> getArticulosPorProfesorAndEstado(@PathVariable("idTutor")Long idTutor, @PathVariable("estado") String estado ){
        List<ArticuloEntity> lista = articuloService.getArticulosByTutorAndEstado(idTutor, estado);
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( map.map(lista, ArticuloDto[].class) ,HttpStatus.OK);
    }

    @GetMapping(value = "/by/estado/")
    public ResponseEntity<CountStateDto[]> getNumArticulosByEstado() {
        List<CountStateDto> lista = articuloService.conteoByEstado();
        if(Objects.isNull(lista) || lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( map.map(lista, CountStateDto[].class ),HttpStatus.OK);
    }
}
