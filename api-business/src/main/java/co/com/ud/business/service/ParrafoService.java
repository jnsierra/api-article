package co.com.ud.business.service;

import co.com.ud.utiles.dto.ParrafoDto;

import java.util.List;
import java.util.Optional;

public interface ParrafoService {

    Optional<List<ParrafoDto>> obtenerParrafosByIdArt(String token, Long idArt);
}
