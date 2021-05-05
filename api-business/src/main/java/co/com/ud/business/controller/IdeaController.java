package co.com.ud.business.controller;

import co.com.ud.business.service.IdeaService;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.IdeaCompletoDto;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.ProfesoresIdeaDto;
import co.com.ud.utiles.dto.UsuarioDto;
import co.com.ud.utiles.enumeracion.TYPE_PROFESOR;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v.1/ideas")
public class IdeaController {

    private final IdeaService ideaService;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @Autowired
    public IdeaController(IdeaService ideaService, ModelMapper mapper, UsuarioService usuarioService) {
        this.ideaService = ideaService;
        this.mapper = mapper;
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/by/usuarios/")
    public ResponseEntity<IdeaDto[]> getIdeasByUsuario(@RequestHeader("Authorization")String autenticacion, @RequestParam(name = "id", required = false) Long idUsuario){
        List<IdeaDto> ideas = ideaService.findIdeasByUsuario(autenticacion, idUsuario);
        if(Objects.nonNull(ideas) && !ideas.isEmpty()){
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/by/profesor/{idProfesor}/")
    public ResponseEntity<IdeaDto[]> getIdeasByProfesorAndEstado(@RequestHeader("Authorization")String autenticacion
                                                                , @PathVariable(name = "idProfesor", required = false) Long idProfesor
                                                                , @RequestParam(name = "estado", required = false) String estado
                                                                , @RequestParam(name = "rolProfIdea", required = false) TYPE_PROFESOR type_profesor){
        List<IdeaDto> ideas = ideaService.findByProfesorIdAndEstado(autenticacion,idProfesor, estado, type_profesor);
        if(Objects.nonNull(ideas) && !ideas.isEmpty()){
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<IdeaCompletoDto> getById(@RequestHeader("Authorization")String autenticacion ,@PathVariable(name = "id") Long idIdea){
        Optional<IdeaDto> idea = ideaService.findById(autenticacion, idIdea);
        if(idea.isPresent()){
            Optional<UsuarioDto> usuario = usuarioService.getUserById(idea.get().getUsuarioId());
            if(usuario.isPresent()){
                IdeaCompletoDto ideaCom = mapper.map(idea.get(),IdeaCompletoDto.class);
                ideaCom.setUsuario(usuario.get());
                return new ResponseEntity<>(ideaCom, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/by/")
    public ResponseEntity<IdeaDto[]> getIdeasByEstado(@RequestHeader("Authorization")String autenticacion, @RequestParam(name = "estado")String estado){
        List<IdeaDto> ideas = ideaService.findByEstado(autenticacion, estado);
        if(Objects.nonNull(ideas) && !ideas.isEmpty()){
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/by/profesores/{idIdea}" )
    public ResponseEntity<ProfesoresIdeaDto> getProfByIdIdea(@RequestHeader("Authorization")String autenticacion
                                                            , @PathVariable(name = "idIdea") Long idIdea){
        Optional<ProfesoresIdeaDto> profesores = ideaService.getProfesoresByIdIdea(autenticacion, idIdea);
        if(profesores.isPresent()){
            return new ResponseEntity<>(profesores.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
