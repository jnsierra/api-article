package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.IdeaService;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final IdeaCliente ideaCliente;
    private final UsuarioCliente usuarioCliente;

    @Autowired
    public IdeaServiceImpl(IdeaCliente ideaCliente, UsuarioCliente usuarioCliente ) {
        this.ideaCliente = ideaCliente;
        this.usuarioCliente = usuarioCliente;
    }

    @Override
    public List<IdeaDto> findIdeasByUsuario(Long idUsuario) {
        List<IdeaDto> ideas = new ArrayList<>();
        ResponseEntity<IdeaDto[]> idea = ideaCliente.getIdeasByUsuario(idUsuario);
        if(HttpStatus.OK.equals(idea.getStatusCode())){
            ideas = Arrays.asList(idea.getBody());
            ideas = ideas.stream().parallel()
                    .map(item -> {
                        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserById(item.getId_profesor());
                        if(HttpStatus.OK.equals(response.getStatusCode())){
                            UsuarioDto profesor = response.getBody();
                            item.setNombreProf(profesor.getPersona().getApellidos() + " " + profesor.getPersona().getNombres());
                        }
                        return item;
                    }).collect(Collectors.toList());
        }
        return ideas;
    }

    @Override
    public List<IdeaDto> findByProfesorIdAndEstado(Long idProfesor, String estado) {
        List<IdeaDto> ideas = new ArrayList<>();
        ResponseEntity<IdeaDto[]> idea = ideaCliente.getIdeasByProfesorAndEstado(idProfesor, estado);
        if(HttpStatus.OK.equals(idea.getStatusCode()) && idea.getBody().length != 0){
            ideas = Arrays.asList(idea.getBody());
            ideas = ideas.stream().parallel()
                    .map(item -> {
                        Optional<String> nomAlum = getNombreUsuario(item.getId_profesor());
                        item.setNombreAlumno(nomAlum.isPresent() ? nomAlum.get() : "");
                        return item;
                    }).collect(Collectors.toList());
        }
        return ideas;
    }

    @Override
    public Optional<IdeaDto> findById(Long idIdea) {
        ResponseEntity<IdeaDto> idea = ideaCliente.getById(idIdea);
        if(HttpStatus.OK.equals(idea.getStatusCode())){
            IdeaDto responseIdea = idea.getBody();
            Optional<String> nombreUsurio  = Objects.isNull(responseIdea.getUsuarioId()) ? Optional.empty() : getNombreUsuario(responseIdea.getUsuarioId());
            Optional<String> nombreProfAsi = Objects.isNull(responseIdea.getId_profesor()) ? Optional.empty() : getNombreUsuario(responseIdea.getId_profesor());
            Optional<String> nombreProfAut = Objects.isNull(responseIdea.getIdProfesorAutoriza()) ? Optional.empty() : getNombreUsuario(responseIdea.getIdProfesorAutoriza());
            responseIdea.setNombreAlumno(nombreUsurio.isPresent() ? nombreUsurio.get() : "" );
            responseIdea.setProfesorAsignado(nombreProfAsi.isPresent() ? nombreProfAsi.get() : "" );
            responseIdea.setProfesorAutoriza(nombreProfAut.isPresent() ? nombreProfAut.get() : "");

            return Optional.of(responseIdea);
        }
        return Optional.empty();
    }

    private Optional<String> getNombreUsuario(Long idUsuario){
        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserById(idUsuario);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            UsuarioDto profesor = response.getBody();
            return Optional.of(profesor.getPersona().getApellidos() + " " + profesor.getPersona().getNombres());
        }
        return Optional.empty();
    }
}
