package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.CountStateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${endpoint.ms-acceso-datos.articulos.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.articulos.version}${endpoint.ms-acceso-datos.articulos.url}")
public interface ArticuloCliente {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ArticuloDto> save(@RequestHeader("Authorization") String token, @RequestBody ArticuloDto articuloDto);

    @PutMapping(value = "/cambiarestado/")
    ResponseEntity<ArticuloDto> updateEstadoArticulo(@RequestHeader("Authorization") String token, @RequestParam("idArticulo") Long idArt, @RequestParam("estado") String estado);

    @GetMapping(value = "/by/{idArt}/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ArticuloDto> getById(@RequestHeader("Authorization") String token, @PathVariable Long idArt);

    @PutMapping(value = "/cambiarUbicFormato/")
    ResponseEntity<ArticuloDto> updateUbicacionFormato(@RequestHeader("Authorization") String token,
                                                       @RequestBody ArticuloDto articuloDto);

    @GetMapping(value = "/by/estado/")
    ResponseEntity<CountStateDto[]> getNumArticulosByEstado(@RequestHeader("Authorization") String token);

}
