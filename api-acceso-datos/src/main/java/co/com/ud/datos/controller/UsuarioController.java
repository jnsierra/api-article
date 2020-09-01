package co.com.ud.datos.controller;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.service.UsuarioService;
import co.com.ud.utiles.dto.UsuarioDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<UsuarioDto[]> get(){
        List<UsuarioEntity> usuarios = usuarioService.getAll();
        return new ResponseEntity<>(mapper.map(usuarios, UsuarioDto[].class), HttpStatus.OK);
    }
}