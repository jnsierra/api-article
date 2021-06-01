package co.com.ud.business.controller;

import co.com.ud.business.service.FormatoService;
import co.com.ud.utiles.dto.FormatoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/formato")
public class FormatoController {

    private final FormatoService formatoService;

    @Autowired
    public FormatoController(FormatoService formatoService) {
        this.formatoService = formatoService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormatoDto> save(@RequestHeader("Authorization")String autenticacion, @RequestBody FormatoDto formato){
        Optional<FormatoDto> response = formatoService.guardarFormatoArt(autenticacion,formato);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}
