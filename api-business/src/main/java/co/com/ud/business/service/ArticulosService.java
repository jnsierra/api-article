package co.com.ud.business.service;

import co.com.ud.utiles.dto.ArticuloDto;

import java.util.Optional;

public interface ArticulosService {

    Optional<ArticuloDto> save(String token, ArticuloDto articuloDto);
}
