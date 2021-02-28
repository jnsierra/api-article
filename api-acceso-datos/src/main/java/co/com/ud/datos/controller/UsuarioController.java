package co.com.ud.datos.controller;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.service.UsuarioService;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.USER_STATE;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/usuarios")
public class UsuarioController {


    private final ModelMapper mapper;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(ModelMapper mapper, UsuarioService usuarioService) {
        this.mapper = mapper;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto> save(@RequestBody(required = true) UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = usuarioService.save(mapper.map(usuarioDto, UsuarioEntity.class));
        return new ResponseEntity<>(mapper.map(usuarioEntity, UsuarioDto.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto[]> get() {
        List<UsuarioEntity> usuarios = usuarioService.getAll();
        return new ResponseEntity<>(mapper.map(usuarios, UsuarioDto[].class), HttpStatus.OK);
    }

    @GetMapping(value = "/by/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDto[]> getUserByFilters(@RequestParam(name = "correo", required = false) String correo
            , @RequestParam(name = "contrasenia", required = false) String contrasenia
            , @RequestParam(name = "tipoUsuario", required = false) String tipoUsuario) {
        List<UsuarioEntity> usuarios = new ArrayList<>();
        if(Objects.nonNull(correo) && Objects.isNull(tipoUsuario)){
            Optional<UsuarioEntity> usuario = usuarioService.getFiltersUniques(correo, contrasenia);
            if (usuario.isPresent()) {
                usuarios.add(usuario.get());
            }
        }else if(Objects.nonNull(tipoUsuario)){
            usuarios = usuarioService.getUserByTipoUsuario(tipoUsuario);
        }
        if(usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.map(usuarios, UsuarioDto[].class), HttpStatus.OK);

    }

    @PutMapping(value = "/{correo}/intentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateIntentosUser(@PathVariable("correo")String correo){
        Optional<Boolean> respuesta =  usuarioService.updateIntentosLoginUsuario(correo);
        if(respuesta.isPresent()){
            return new ResponseEntity<Boolean>(respuesta.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<UsuarioDto> getUserById(@PathVariable("id")Long id){
        Optional<UsuarioEntity> usuario = usuarioService.getUserById(id);
        if(usuario.isPresent()){
            return new ResponseEntity<>(mapper.map(usuario.get(), UsuarioDto.class),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Boolean> updateEstadoTipoUsuario(@RequestParam("id") Long id, @RequestParam("estado")USER_STATE estado,
                                                           @RequestParam("tipoUsuario") Long tipoUsuario){
        Optional<Boolean> response = usuarioService.modifyEstadoTipoUsuario(id, estado, tipoUsuario);

        if(response.isPresent() && Boolean.TRUE.equals(response.get()))
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}