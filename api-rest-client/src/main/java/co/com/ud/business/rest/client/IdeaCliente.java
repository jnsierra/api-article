package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.IdeaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${endpoint.ms-acceso-datos.ideas.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.ideas.version}${endpoint.ms-acceso-datos.ideas.url}")
public interface IdeaCliente {

    @GetMapping(value = "/by/usuarios/")
    ResponseEntity<IdeaDto[]> getIdeasByUsuario(@RequestParam(name = "id", required = false) Long id);

    @GetMapping(value = "/by/profesor/{idProfesor}/")
    ResponseEntity<IdeaDto[]> getIdeasByProfesorAndEstado(@PathVariable(name = "idProfesor", required = false) Long idProfesor
            , @RequestParam(name = "estado", required = false) String estado);
}
