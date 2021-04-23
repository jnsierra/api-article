package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.rest.client.IdeaCliente;
import co.com.ud.business.service.ArticulosService;
import co.com.ud.utiles.dto.ArticuloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticuloServiceImpl implements ArticulosService {

    private final ArticuloCliente articuloCliente;
    private final IdeaCliente ideaCliente;

    @Autowired
    public ArticuloServiceImpl(ArticuloCliente articuloCliente, IdeaCliente ideaCliente) {
        this.articuloCliente = articuloCliente;
        this.ideaCliente = ideaCliente;
    }

    @Override
    public Optional<ArticuloDto> save(String token, ArticuloDto articuloDto) {
        ResponseEntity<ArticuloDto> response = articuloCliente.save(token, articuloDto);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            //Cambiamos el estado de la idea a ARTICULO_EN_PROCESO
            ResponseEntity<Boolean> responseCliente = ideaCliente.updateStatusIdea(token, articuloDto.getIdeaId(), "ARTICULO_EN_PROCESO");
            if(HttpStatus.OK.equals(responseCliente.getStatusCode()) && Boolean.TRUE.equals(responseCliente.getBody())){
                return Optional.of(response.getBody());
            }
        }
        return Optional.empty();
    }
}
