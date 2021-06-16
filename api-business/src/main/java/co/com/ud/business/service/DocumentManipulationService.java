package co.com.ud.business.service;

import java.io.IOException;
import java.util.Optional;

public interface DocumentManipulationService {

    Optional<String> generateFormatArt(String token, Long idArt) throws IOException;

    Optional<String> validaEtiquetasFormato(String token, String ubicacion)throws IOException ;
}
