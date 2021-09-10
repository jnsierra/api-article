package co.com.ud.business.controller;

import co.com.ud.business.service.PersonaService;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.PersonaDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/usuarios")
public class UsuariosController {

    private UsuarioService usuarioService;
    private PersonaService personaService;

    @Autowired
    public UsuariosController(UsuarioService usuarioService, PersonaService personaService) {
        this.usuarioService = usuarioService;
        this.personaService = personaService;
    }

    @GetMapping("/by/")
    public ResponseEntity<UsuarioDto> getUsersByEmail(@RequestParam(name = "correo") String correo) {
        Optional<UsuarioDto> usuario = usuarioService.getUsuarioByCorreo(correo);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getUsers() {
        List<UsuarioDto> usuarios = usuarioService.getUsers();
        if (usuarios.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> getUserById(@PathVariable("id")Long id){
        Optional<UsuarioDto> usuario = usuarioService.getUserById(id);
        if(usuario.isPresent()){
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> save(@RequestBody @Valid UsuarioDto usuario){
        //Creamos la entidad Persona
        Optional<PersonaDto> persona = personaService.save(usuario.getPersona());
        if(persona.isPresent()){
            usuario.setPersona(persona.get());
            Optional<UsuarioDto> usuarioResponse = usuarioService.save(usuario);
            if(usuarioResponse.isPresent()){
                return new ResponseEntity<>(usuarioResponse.get(), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/recuperarPass/{correo}/")
    public ResponseEntity<Boolean> generatePasswordTemporal(@RequestHeader("Authorization")String autenticacion, @PathVariable("correo")String correo){
        Boolean response = usuarioService.recuperarContrasenia(autenticacion, correo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
