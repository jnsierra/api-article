package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "usuario", url = "http://localhost:5003/api-datos/v.1/usuarios")
@FeignClient(name = "${endpoint.ms-acceso-datos.usuarios.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.usuarios.version}${endpoint.ms-acceso-datos.usuarios.url}")
public interface UsuarioCliente {

    @GetMapping(value = "/by/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsuarioDto> getUserByEmailAndPass(@RequestParam(name = "correo", required = true) String correo, @RequestParam(name = "contrasenia", required = false) String contrasenia);

    @GetMapping(value = "/by/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UsuarioDto> getUserByEmail(@RequestParam(name = "correo", required = true) String correo);

}
