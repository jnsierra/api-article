package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.ArticuloDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${endpoint.ms-acceso-datos.articulos.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.articulos.version}${endpoint.ms-acceso-datos.articulos.url}")
public interface ArticuloCliente {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ArticuloDto> save(@RequestHeader("Authorization") String token, @RequestBody ArticuloDto articuloDto);

}
