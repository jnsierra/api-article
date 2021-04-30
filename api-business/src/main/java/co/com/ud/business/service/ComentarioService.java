package co.com.ud.business.service;

import co.com.ud.utiles.dto.ComentarioDto;

import java.util.Optional;

public interface ComentarioService {

    Optional<ComentarioDto> saveWithFile(String token,ComentarioDto comentarioDto);
}
