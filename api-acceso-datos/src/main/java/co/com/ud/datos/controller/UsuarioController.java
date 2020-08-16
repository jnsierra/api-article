package co.com.ud.datos.controller;

import co.com.ud.datos.entity.UsuarioEntity;
import co.com.ud.datos.service.UsuarioService;
import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;

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
    public ResponseEntity<UsuarioDto> save(UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = usuarioService.save(mapper.map(usuarioDto, UsuarioEntity.class));
        return new ResponseEntity<>(mapper.map(usuarioEntity, UsuarioDto.class), HttpStatus.CREATED);
    }


}
