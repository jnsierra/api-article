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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                        ResponseEntity<UsuarioDto> response = usuarioCliente.getUserById(item.getUsuarioId());
                        if(HttpStatus.OK.equals(response.getStatusCode())){
                            UsuarioDto profesor = response.getBody();
                            item.setNombreAlumno(profesor.getPersona().getApellidos() + " " + profesor.getPersona().getNombres());
                        }
                        return item;
                    }).collect(Collectors.toList());
        }
        return ideas;
    }
}
