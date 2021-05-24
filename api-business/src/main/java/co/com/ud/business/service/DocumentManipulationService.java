package co.com.ud.business.service;

import java.io.IOException;
import java.util.Optional;

public interface DocumentManipulationService {

    Optional<String> generateFormatArt(String token, Long idArt) throws IOException;
}
