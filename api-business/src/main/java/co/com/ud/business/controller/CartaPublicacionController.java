package co.com.ud.business.controller;

import co.com.ud.business.service.CartaPublicacionService;
import co.com.ud.utiles.dto.DocumentoUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/carta-publicacion")
public class CartaPublicacionController {

    private final CartaPublicacionService cartaPublicacionService;

    @Autowired
    public CartaPublicacionController(CartaPublicacionService cartaPublicacionService) {
        this.cartaPublicacionService = cartaPublicacionService;
    }

    @PostMapping(value = "/{idArt}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<DocumentoUploadDto> uploadCartaPublicacion(@RequestHeader("Authorization")String autenticacion,
                                                                     @RequestBody DocumentoUploadDto documentoUploadDto,
                                                                     @PathVariable("idArt") Long idArt){
        Optional<DocumentoUploadDto> response = this.cartaPublicacionService.persistirCarta(autenticacion, idArt, documentoUploadDto);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response.get(),HttpStatus.OK);
    }

}
