package co.com.ud.business.controller;

import co.com.ud.business.service.DescargaFormatoService;
import co.com.ud.utiles.dto.DocumentDownloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v.1/downloadFiles")
public class DownloadFilesController {

    private DescargaFormatoService descargaFormatoService;

    @Autowired
    public DownloadFilesController(DescargaFormatoService descargaFormatoService) {
        this.descargaFormatoService = descargaFormatoService;
    }

    @GetMapping(value = "/formatoIdea/")
    public ResponseEntity<DocumentDownloadDto> getFormato(){
        Optional<DocumentDownloadDto> descarga = descargaFormatoService.descargarFormatoIdea();
        if(descarga.isPresent()){
            return new ResponseEntity<>(descarga.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/formatoAdjuntoIdea/{idIdea}/")
    public ResponseEntity<DocumentDownloadDto> getFormatoIdea(@RequestHeader("Authorization")String autenticacion
            ,@PathVariable(name = "idIdea") Long idIdea){
        Optional<DocumentDownloadDto> descarga = descargaFormatoService.descargarFormatoIdeaByIdIdea(autenticacion,idIdea);
        if(descarga.isPresent()){
            return new ResponseEntity<>(descarga.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/")
    public ResponseEntity<DocumentDownloadDto> getDocByUrl(@RequestBody DocumentDownloadDto documentDownloadDto){
        Optional<DocumentDownloadDto> descarga = descargaFormatoService.descargarDocumento(documentDownloadDto.getUbicacion());
        if(descarga.isPresent()){
            return new ResponseEntity<>(descarga.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}