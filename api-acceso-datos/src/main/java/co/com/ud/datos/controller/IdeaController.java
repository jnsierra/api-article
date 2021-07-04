package co.com.ud.datos.controller;

import co.com.ud.datos.entity.IdeaEntity;
import co.com.ud.datos.service.IdeaService;
import co.com.ud.utiles.dto.CountStateDto;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.enumeracion.TYPE_PROFESOR;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaDto> save(@RequestBody IdeaDto idea) {
        Optional<IdeaEntity> ideaEntity = ideaService.save(mapper.map(idea, IdeaEntity.class));
        if (ideaEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.map(ideaEntity.get(), IdeaDto.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/by/usuarios/")
    public ResponseEntity<IdeaDto[]> getIdeasByUsuario(@RequestParam(name = "id", required = false) Long idUsuario) {
        List<IdeaEntity> ideas = ideaService.findByUsuarioId(idUsuario);
        if (Objects.nonNull(ideas) && !ideas.isEmpty())
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/by/estado/")
    public ResponseEntity<CountStateDto[]> getIdeasNumIdeasByEstado() {
        List<CountStateDto> response = ideaService.conteoByEstado();
        if (Objects.isNull(response) || response.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(mapper.map(response, CountStateDto[].class), HttpStatus.OK);
    }

    @GetMapping(value = "/by/profesor/{idProfesor}/")
    public ResponseEntity<IdeaDto[]> getIdeasByProfesorAndEstado(@PathVariable(name = "idProfesor", required = false) Long idProfesor
            , @RequestParam(name = "estado", required = false) String estado
            , @RequestParam(name = "rolIdea", required = false) TYPE_PROFESOR typeProfesor) {
        List<IdeaEntity> ideas = ideaService.findByProfesorIdAndEstado(idProfesor, estado, typeProfesor);
        if (Objects.nonNull(ideas) && !ideas.isEmpty())
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/by/")
    public ResponseEntity<IdeaDto[]> getIdeasByEstado(@RequestParam(name = "estado") String estado) {
        List<IdeaEntity> ideas = ideaService.findByEstado(estado);
        if (Objects.nonNull(ideas) && !ideas.isEmpty()) {
            return new ResponseEntity<>(mapper.map(ideas, IdeaDto[].class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/estado/{idIdea}/{estado}/{idUsuario}/")
    public ResponseEntity<Boolean> updateStatusIdeaByIdAndUsuario(@PathVariable(name = "idIdea") Long id
            , @PathVariable(name = "estado") String estado
            , @PathVariable(name = "idUsuario") Long idUsuario) {
        Optional<Boolean> rta = ideaService.modificarIdProfAutorizaAndEstadoAndFechaAutoriza(id, idUsuario, estado);
        if (rta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rta.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/estado/{idIdea}/{estado}/")
    public ResponseEntity<Boolean> updateStatusIdea(@PathVariable(name = "idIdea") Long id
            , @PathVariable(name = "estado") String estado) {
        Optional<Boolean> rta = ideaService.modificarEstado(id, estado);
        if (rta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rta.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/jurado/{idIdea}/{idJurado}")
    public ResponseEntity<Boolean> updateJuradoIdea(@PathVariable(name = "idIdea") Long idIdea,
                                                    @PathVariable(name = "idJurado") Long idJurado) {
        Optional<Boolean> rta = ideaService.modificarJurado(idIdea, idJurado);
        if (rta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rta.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<IdeaDto> getById(@PathVariable(name = "id") Long idIdea) {
        Optional<IdeaEntity> idea = ideaService.findById(idIdea);
        if (idea.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mapper.map(idea.get(), IdeaDto.class), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Boolean> updateIdea(@RequestBody IdeaDto idea) {
        Optional<Boolean> response = ideaService.modificaIdea(mapper.map(idea, IdeaEntity.class));
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
}
