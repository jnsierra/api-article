package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.business.service.IdeaService;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.ProfesoresIdeaDto;
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
    public List<IdeaDto> findIdeasByUsuario(String token, Long idUsuario) {
        List<IdeaDto> ideas = new ArrayList<>();
        ResponseEntity<IdeaDto[]> idea = ideaCliente.getIdeasByUsuario(token, idUsuario);
        if(HttpStatus.OK.equals(idea.getStatusCode()) && Objects.nonNull(idea.getBody())){
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
    public List<IdeaDto> findByProfesorIdAndEstado(String token, Long idProfesor, String estado) {
        List<IdeaDto> ideas = new ArrayList<>();
        ResponseEntity<IdeaDto[]> idea = ideaCliente.getIdeasByProfesorAndEstado(token,idProfesor, estado);
        if(HttpStatus.OK.equals(idea.getStatusCode()) && idea.getBody().length != 0){
            ideas = Arrays.asList(idea.getBody());
            ideas = ideas.stream().parallel()
                    .map(item -> {
                        Optional<String> nomAlum = getNombreUsuario(item.getUsuarioId());
                        item.setNombreAlumno(nomAlum.isPresent() ? nomAlum.get() : "");
                        return item;
                    }).collect(Collectors.toList());
        }
        return ideas;
    }

    @Override
    public Optional<IdeaDto> findById(String token, Long idIdea) {
        ResponseEntity<IdeaDto> idea = ideaCliente.getById(token, idIdea);
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

    @Override
    public Optional<Boolean> updateStatus(String token,Long idIdea, String status) {
        ResponseEntity<Boolean> response = this.ideaCliente.updateStatusIdea(token, idIdea, status);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    @Override
    public List<IdeaDto> findByEstado(String token, String estado) {
        List<IdeaDto> ideas = new ArrayList<>();
        ResponseEntity<IdeaDto[]> idea = ideaCliente.getIdeasByEstado(token, estado);
        if(HttpStatus.OK.equals(idea.getStatusCode())){
            ideas = Arrays.asList(idea.getBody());
            ideas = ideas.stream().parallel()
                    .map(item -> {
                        Optional<String> nombreProf = getNombreUsuario(item.getId_profesor());
                        if(nombreProf.isPresent()){
                            item.setNombreProf(nombreProf.get());
                        }
                        return item;
                    }).collect(Collectors.toList());
        }
        return ideas;
    }

    private Optional<String> getNombreUsuario(Long idUsuario){
        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserById(idUsuario);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            UsuarioDto profesor = response.getBody();
            return Optional.of(profesor.getPersona().getApellidos() + " " + profesor.getPersona().getNombres());
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProfesoresIdeaDto> getProfesoresByIdIdea(String token, Long idIdea) {
        ResponseEntity<IdeaDto> ideaResponse = ideaCliente.getById(token, idIdea);
        if(HttpStatus.OK.equals(ideaResponse.getStatusCode()) && Objects.nonNull(ideaResponse.getBody())){
            IdeaDto response = ideaResponse.getBody();
            Optional<String> nombreProfAsi = Objects.isNull(response.getId_profesor()) ? Optional.empty() : getNombreUsuario(response.getId_profesor());
            Optional<String> nombreProfAut = Objects.isNull(response.getIdProfesorAutoriza()) ? Optional.empty() : getNombreUsuario(response.getIdProfesorAutoriza());
            Optional<String> nombreProfJur = Objects.isNull(response.getIdProfesorJurado()) ? Optional.empty() : getNombreUsuario(response.getIdProfesorJurado());

            ProfesoresIdeaDto profesor = ProfesoresIdeaDto.builder()
                    .idIdea(response.getId())
                    .idProAsig(response.getId_profesor())
                    .nombreProAsig( nombreProfAsi.orElse("")  )
                    .idProfTutor(response.getIdProfesorAutoriza())
                    .nombreProTutor( nombreProfAut.orElse("") )
                    .idProfJurado(response.getIdProfesorJurado())
                    .nombreProJurado(nombreProfJur.orElse(""))
                    .build();
            return Optional.of(profesor);
        }
        return Optional.empty();
    }
}
