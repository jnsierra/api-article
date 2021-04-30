package co.com.ud.business.controller;

import co.com.ud.business.service.ComentarioService;
import co.com.ud.utiles.dto.ComentarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping(value = "/file")
    public ResponseEntity<ComentarioDto> saveWithFile(@RequestHeader("Authorization")String autenticacion, @RequestBody ComentarioDto comentario){
        Optional<ComentarioDto> response = this.comentarioService.saveWithFile(autenticacion, comentario);
        if (response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
