package co.com.ud.business.service;

import co.com.ud.utiles.dto.FormatoDto;

import java.util.Optional;

public interface FormatoService {

    Optional<FormatoDto> guardarFormatoArt(String token, FormatoDto formato);

    Optional<FormatoDto> save(String token, FormatoDto formato);
}
