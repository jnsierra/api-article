package co.com.ud.datos.controller;

import co.com.ud.datos.entity.FormatoEntity;
import co.com.ud.datos.service.FormatoService;
import co.com.ud.utiles.dto.FormatoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/formato")
public class FormatoController {

    private final FormatoService formatoService;
    private final ModelMapper mappper;

    @Autowired
    public FormatoController(FormatoService formatoService, ModelMapper mappper) {
        this.formatoService = formatoService;
        this.mappper = mappper;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormatoDto> save(@RequestBody FormatoDto formatoDto){
        Optional<FormatoEntity> response = formatoService.save(mappper.map(formatoDto, FormatoEntity.class));
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mappper.map(response.get(), FormatoDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{idArt}/",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormatoDto[]> getFormatoByIdArt(@PathVariable("idArt")Long idArt){
        List<FormatoEntity> response = formatoService.findByIdArt(idArt);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( mappper.map(response, FormatoDto[].class),HttpStatus.OK);
    }
}