package co.com.ud.business.rest.client;

import co.com.ud.business.config.FeignConfigInterceptor;
import co.com.ud.utiles.dto.PersonaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "${endpoint.ms-acceso-datos.personas.name}",
//        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.personas.version}${endpoint.ms-acceso-datos.personas.url}")
//@FeignClient(value = "api-acceso-datos2",url = "${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.personas.version}${endpoint.ms-acceso-datos.personas.url}")
@FeignClient(name = "api-acceso-datos"
        , contextId = "${endpoint.ms-acceso-datos.personas.name}"
        , path = "${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.personas.version}${endpoint.ms-acceso-datos.personas.url}"
        , configuration = {FeignConfigInterceptor.class}
)
public interface PersonaCliente {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PersonaDto> save(@RequestBody(required = true) PersonaDto personaDto);
}
