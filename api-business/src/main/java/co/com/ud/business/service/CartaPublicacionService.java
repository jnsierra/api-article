package co.com.ud.business.service;

import co.com.ud.utiles.dto.DocumentoUploadDto;

import java.util.Optional;

public interface CartaPublicacionService {

    Optional<DocumentoUploadDto> persistirCarta(String token, Long idArt, DocumentoUploadDto documento);
}
