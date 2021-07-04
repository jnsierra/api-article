package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.rest.client.ComentarioArticuloClient;
import co.com.ud.business.rest.client.ControlLecturaClient;
import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.business.service.ArticulosService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.ComentarioArticuloDto;
import co.com.ud.utiles.dto.ControlLecturaDto;
import co.com.ud.utiles.dto.CountStateDto;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticulosService {

    private final ArticuloCliente articuloCliente;
    private final IdeaCliente ideaCliente;
    private final ComentarioArticuloClient comentarioArticuloClient;
    private final ControlLecturaClient controlLecturaClient;

    @Autowired
    public ArticuloServiceImpl(ArticuloCliente articuloCliente, IdeaCliente ideaCliente, ComentarioArticuloClient comentarioArticuloClient, ControlLecturaClient controlLecturaClient) {
        this.articuloCliente = articuloCliente;
        this.ideaCliente = ideaCliente;
        this.comentarioArticuloClient = comentarioArticuloClient;
        this.controlLecturaClient = controlLecturaClient;
    }

    @Override
    public Optional<ArticuloDto> save(String token, ArticuloDto articuloDto) {
        ResponseEntity<ArticuloDto> response = articuloCliente.save(token, articuloDto);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            //Cambiamos el estado de la idea a ARTICULO_EN_PROCESO
            ResponseEntity<Boolean> responseCliente = ideaCliente.updateStatusIdea(token, articuloDto.getIdeaId(), "ARTICULO_EN_PROCESO");
            if(HttpStatus.OK.equals(responseCliente.getStatusCode()) && Objects.nonNull(response.getBody()) &&Boolean.TRUE.equals(responseCliente.getBody())){
                return Optional.of(response.getBody());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<ArticuloDto> revisionArticulo(String token,Long idArt) {
        Optional<String> comentarios = this.comentariosArticuloSinRespuesta(token, idArt);
        Assert.isTrue(comentarios.isEmpty(), "Existen comentarios sin respuesta en: " + (comentarios.isPresent() ? comentarios.get() : ""));
        Assert.isTrue(this.cuentaControlesLectura(token,idArt), "Articulo sin bibliografia minima requerida");
        Optional<ArticuloDto> responseUpdate = this.actualizarEstadoArticulo(token, idArt, "REVISAR_PROFESOR");
        Assert.isTrue(responseUpdate.isPresent(), "No fue posible actualizar el estado del articulo");
        return responseUpdate;
    }

    @Override
    public Optional<ArticuloDto> aprobacionArticulo(String token, Long idArt) {
        Optional<String> comentarios = this.comentariosArticuloSinRespuesta(token, idArt);
        Assert.isTrue(comentarios.isEmpty(), "Haz realizado un comentario en " + (comentarios.isPresent() ? comentarios.get() : "") + " el cual debe ser respondido por el alumno. No es posible aprobar el documento");
        Optional<ArticuloDto> responseUpdate = this.actualizarEstadoArticulo(token, idArt, "ARTICULO_APROBADO");
        Assert.isTrue(responseUpdate.isPresent(), "No fue posible actualizar el estado del articulo");
        return responseUpdate;
    }

    @Override
    public Optional<CountStateDto[]> getNumArticulosByEstado(String token) {
        ResponseEntity<CountStateDto[]> response = articuloCliente.getNumArticulosByEstado(token);
        if(Objects.nonNull(response) && HttpStatus.OK.equals(response.getStatusCode())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    private Optional<String> comentariosArticuloSinRespuesta(String token, Long idArt){
        ResponseEntity<ComentarioArticuloDto[]> response = comentarioArticuloClient.getComentariosByArtId(token, idArt);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
            for(ComentarioArticuloDto item : response.getBody()){
                if(Objects.isNull(item.getRespuestaComentario()) || "".equals(item.getRespuestaComentario())){
                    return Optional.of(item.getTypeComentarioArt().toString());
                }
            }
        }
        return Optional.empty();
    }

    private Boolean cuentaControlesLectura(String token, Long idArt){
        ResponseEntity<ControlLecturaDto[]> response = controlLecturaClient.getByArticuloId(token, idArt);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
            return response.getBody().length > 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        return Boolean.FALSE;
    }

    private Optional<ArticuloDto> actualizarEstadoArticulo(String token, Long idArt, String estado){
        ResponseEntity<ArticuloDto> response = articuloCliente.updateEstadoArticulo(token, idArt, estado);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }
}
