package co.com.ud.business.controller;

import co.com.ud.business.service.IdeaService;
import co.com.ud.utiles.dto.IdeaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v.1/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final ModelMapper mapper;

    @Autowired
    public IdeaController(IdeaService ideaService, ModelMapper mapper) {
        this.ideaService = ideaService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/by/usuarios/")
    public ResponseEntity<IdeaDto[]> getIdeasByUsuario(@RequestParam(name = "id", required = false) Long idUsuario){
        List<IdeaDto> ideas = ideaService.findIdeasByUsuario(idUsuario);
        if(Objects.nonNull(ideas) && !ideas.isEmpty()){
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
