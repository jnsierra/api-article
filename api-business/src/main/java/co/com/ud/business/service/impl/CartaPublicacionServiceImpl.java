package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.service.CartaPublicacionService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.DocumentoUploadDto;
import co.com.ud.utiles.service.UtilesBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CartaPublicacionServiceImpl implements CartaPublicacionService {

    private final String pathRepo;
    private final ArticuloCliente articuloCliente;

    @Autowired
    public CartaPublicacionServiceImpl(@Value("${repositorio.formatoArt.cartaPublicacion}")  String pathRepo, ArticuloCliente articuloCliente) {
        this.pathRepo = pathRepo;
        this.articuloCliente = articuloCliente;
    }

    @Override
    public Optional<DocumentoUploadDto> persistirCarta(String token, Long idArt, DocumentoUploadDto documento) {
        String nombre = this.generoNombreCartaPublicacion(idArt, documento.getExtension());
        Boolean guardar = UtilesBase64.builder().build().saveFile(documento.getBase64(),pathRepo, nombre);
        if(guardar){
            //Actualizar el articulo con la ubicacion del formato
            ResponseEntity<ArticuloDto> response = this.articuloCliente.updateUbicacionCartaPublicacion(token, ArticuloDto.builder()
                    .id(idArt)
                    .ubicacion_carta_publicacion(pathRepo + nombre)
                    .build());
            if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
                documento.setUbicacion(response.getBody().getUbicacion_carta_publicacion());
                return Optional.of(documento);
            }

        }
        return Optional.empty();
    }

    private String generoNombreCartaPublicacion (Long idArt, String extension){
        String nombre = "CARTA_PUBLICACION_" + idArt + "." + extension;
        return nombre;
    }
}
