package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ParrafoClient;
import co.com.ud.business.service.ParrafoService;
import co.com.ud.utiles.dto.ParrafoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParrafoServiceImpl implements ParrafoService {

    private final ParrafoClient parrafoClient;

    @Autowired
    public ParrafoServiceImpl(ParrafoClient parrafoClient) {
        this.parrafoClient = parrafoClient;
    }

    @Override
    public Optional<List<ParrafoDto>> obtenerParrafosByIdArt(String token, Long idArt) {
        ResponseEntity<ParrafoDto[]> response = parrafoClient.getParrafoByArticulo(token, idArt);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
            return Optional.of(Arrays.asList(response.getBody()));
        }
        return Optional.empty();
    }
}
