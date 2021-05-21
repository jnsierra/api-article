package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.ComentarioArticuloDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${endpoint.ms-acceso-datos.comentariosArt.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.comentariosArt.version}${endpoint.ms-acceso-datos.comentariosArt.url}")
public interface ComentarioArticuloClient {

    @GetMapping(value = "/ariculo/{idArt}/")
    ResponseEntity<ComentarioArticuloDto[]> getComentariosByArtId(@RequestHeader("Authorization") String token, @PathVariable("idArt") Long idArticulo);
}
