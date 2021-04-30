package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.ComentarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${endpoint.ms-acceso-datos.comentarios.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.comentarios.version}${endpoint.ms-acceso-datos.comentarios.url}")
public interface ComentarioClient {

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ComentarioDto> save(@RequestHeader("Authorization") String token, @RequestBody ComentarioDto comentarioDto);

    @PutMapping(value = "/ubicacion", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> updateUbicacionComentario(@RequestHeader("Authorization") String token,
                                                      @RequestBody ComentarioDto comentarioDto);

}
