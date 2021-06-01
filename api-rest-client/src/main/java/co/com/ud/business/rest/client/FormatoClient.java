package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.FormatoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${endpoint.ms-acceso-datos.formato.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.formato.version}${endpoint.ms-acceso-datos.formato.url}")
public interface FormatoClient {

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FormatoDto> save(@RequestHeader("Authorization") String token, @RequestBody FormatoDto formatoDto);

    @GetMapping(value = "/{idArt}/",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FormatoDto[]> getFormatoByIdArt(@RequestHeader("Authorization") String token,@PathVariable("idArt")Long idArt);

}
