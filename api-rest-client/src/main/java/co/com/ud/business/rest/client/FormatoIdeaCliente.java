package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.FormatoIdeaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${endpoint.ms-acceso-datos.formato-ideas.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.formato-ideas.version}${endpoint.ms-acceso-datos.formato-ideas.url}")
public interface FormatoIdeaCliente {

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FormatoIdeaDto> save(@RequestHeader("Authorization") String token, @RequestBody FormatoIdeaDto formatoIdeaDto);

    @GetMapping(value = "/by/{idIdea}/")
    ResponseEntity<FormatoIdeaDto[]> getFormatoByIdIdea(@RequestHeader("Authorization") String token, @PathVariable("idIdea")Long idIdea);
}
