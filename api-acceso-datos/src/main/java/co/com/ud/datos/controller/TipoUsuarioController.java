package co.com.ud.datos.controller;

import co.com.ud.datos.entity.TipoUsuarioEntity;
import co.com.ud.datos.service.TipoUsuarioService;
import co.com.ud.utiles.dto.TipoUsuarioDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v.1/tipoUsuarios")
public class TipoUsuarioController {

    private final ModelMapper map;
    private final TipoUsuarioService tipoUsuarioService;

    @Autowired
    public TipoUsuarioController(ModelMapper map, TipoUsuarioService tipoUsuarioService) {
        this.map = map;
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoUsuarioDto> save(@RequestBody(required = true) TipoUsuarioDto tipoUsuarioDto) {
        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioService.save(map.map(tipoUsuarioDto, TipoUsuarioEntity.class));
        return new ResponseEntity<>(map.map(tipoUsuarioEntity, TipoUsuarioDto.class), HttpStatus.CREATED);
    }
}
