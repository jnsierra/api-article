package co.com.ud.business.service;

import co.com.ud.utiles.dto.DocumentDownloadDto;

import java.util.Optional;

public interface DescargaFormatoService {

    Optional<DocumentDownloadDto> descargarFormatoIdea();

    Optional<DocumentDownloadDto> descargarFormatoIdeaByIdIdea(String token, Long idIdea);


}
