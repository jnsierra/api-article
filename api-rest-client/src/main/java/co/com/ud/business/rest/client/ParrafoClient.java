package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.ParrafoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${endpoint.ms-acceso-datos.parrafos.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.parrafos.version}${endpoint.ms-acceso-datos.parrafos.url}")
public interface ParrafoClient {

    @GetMapping(value = "/articulo/{idArticulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ParrafoDto[]> getParrafoByArticulo(@RequestHeader("Authorization") String token, @PathVariable("idArticulo") Long idArt);
}
