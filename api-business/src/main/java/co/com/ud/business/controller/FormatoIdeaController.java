package co.com.ud.business.controller;

import co.com.ud.business.service.FormatoIdeaService;
import co.com.ud.utiles.dto.FormatoIdeaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/formatoidea")
public class FormatoIdeaController {

    private final FormatoIdeaService formatoIdeaService;

    @Autowired
    public FormatoIdeaController(FormatoIdeaService formatoIdeaService) {
        this.formatoIdeaService = formatoIdeaService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormatoIdeaDto> save(@RequestHeader("Authorization")String autenticacion, @RequestBody FormatoIdeaDto formatoIdeaDto){
        System.out.println("Llego al controntroller: ");
        Optional<FormatoIdeaDto> response = formatoIdeaService.persistirFormatoIdea(autenticacion, formatoIdeaDto);
        System.out.println("Llego a la respuesta");
        if(response.isPresent()){
            return new ResponseEntity<>( response.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
