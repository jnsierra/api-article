package co.com.ud.business.service;

import co.com.ud.utiles.dto.FormatoIdeaDto;

import java.util.Optional;

public interface FormatoIdeaService {

    Optional<FormatoIdeaDto> saveFormato(String token,FormatoIdeaDto formatoIdea);

    Optional<FormatoIdeaDto> persistirFormatoIdea(String token,FormatoIdeaDto formatoIdea);

}
