package co.com.ud.datos.controller;

import co.com.ud.datos.entity.ControlLecturaEntity;
import co.com.ud.datos.service.ControlLecturaService;
import co.com.ud.utiles.dto.ControlLecturaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/controllectura")
public class ControlLecturaController {

    private ControlLecturaService controlLecturaService;
    private ModelMapper mapper;

    @Autowired
    public ControlLecturaController(ControlLecturaService controlLecturaService, ModelMapper mapper) {
        this.controlLecturaService = controlLecturaService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ControlLecturaDto> save(@RequestBody ControlLecturaDto controlLecturaDto){
        Optional<ControlLecturaEntity> response = controlLecturaService.save(mapper.map(controlLecturaDto, ControlLecturaEntity.class));
        if(response.isPresent()){
            return new ResponseEntity<>(mapper.map(response.get(), ControlLecturaDto.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/articulo/{idArticulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ControlLecturaDto[]> getByArticuloId( @PathVariable("idArticulo") Long idArticulo){
        List<ControlLecturaEntity> response = controlLecturaService.getByIdArticulo(idArticulo);
        if(!response.isEmpty()){
            return new ResponseEntity<>(mapper.map(response, ControlLecturaDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}