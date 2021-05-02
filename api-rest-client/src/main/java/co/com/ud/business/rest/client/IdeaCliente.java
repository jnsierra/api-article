package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.IdeaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${endpoint.ms-acceso-datos.ideas.name}",
        url = "${endpoint.ms-acceso-datos.protocol}${endpoint.ms-acceso-datos.host}:${endpoint.ms-acceso-datos.port}${endpoint.ms-acceso-datos.base}${endpoint.ms-acceso-datos.ideas.version}${endpoint.ms-acceso-datos.ideas.url}")
public interface IdeaCliente {

    @GetMapping(value = "/by/usuarios/")
    ResponseEntity<IdeaDto[]> getIdeasByUsuario(@RequestHeader("Authorization") String token,
                                                @RequestParam(name = "id", required = false) Long id);

    @GetMapping(value = "/by/profesor/{idProfesor}/")
    ResponseEntity<IdeaDto[]> getIdeasByProfesorAndEstado(@RequestHeader("Authorization") String token
            , @PathVariable(name = "idProfesor", required = false) Long idProfesor
            , @RequestParam(name = "estado", required = false) String estado);

    @GetMapping(value = "/{id}/")
    ResponseEntity<IdeaDto> getById(@RequestHeader("Authorization") String token, @PathVariable(name = "id") Long idIdea);

    @PutMapping(value = "/estado/{idIdea}/{estado}/")
    ResponseEntity<Boolean> updateStatusIdea(@RequestHeader("Authorization") String token,@PathVariable(name = "idIdea") Long id, @PathVariable(name = "estado") String estado);

    @GetMapping(value = "/by/")
    ResponseEntity<IdeaDto[]> getIdeasByEstado(@RequestHeader("Authorization") String token, @RequestParam(name = "estado")String estado);
}
