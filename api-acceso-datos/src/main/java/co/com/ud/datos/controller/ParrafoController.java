package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ParrafoEntity;
import co.com.ud.datos.service.ParrafoService;
import co.com.ud.utiles.dto.ParrafoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/parrafo")
public class ParrafoController {

    private final ParrafoService parrafoService;
    private final ModelMapper model;

    @Autowired
    public ParrafoController(ParrafoService parrafoService, ModelMapper model) {
        this.parrafoService = parrafoService;
        this.model = model;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParrafoDto> save(@RequestBody ParrafoDto parrafoDto){
        Optional<ParrafoEntity> response = parrafoService.save(model.map(parrafoDto, ParrafoEntity.class));
        if(response.isPresent()){
            return new ResponseEntity<>(model.map(response.get(), ParrafoDto.class) , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/articulo/{idArticulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParrafoDto[]> getParrafoByArticulo(@PathVariable("idArticulo") Long idArt){
        List<ParrafoEntity> listParrafo = parrafoService.getParrafoByArticulo(idArt);
        if(!listParrafo.isEmpty()){
            return new ResponseEntity<>(model.map(listParrafo, ParrafoDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{idParrafo}/{orden}/")
    public ResponseEntity<Boolean> modificarOrdenParrafo(@PathVariable(name = "idParrafo") Long idParrafo
                                                        ,@PathVariable(name = "orden") Long orden){
        Optional<Boolean> parrafo = parrafoService.updateOrden(idParrafo, orden);
        if(parrafo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(parrafo.get(), HttpStatus.OK);
    }



}
