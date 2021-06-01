package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.rest.client.FormatoClient;
import co.com.ud.business.service.FormatoService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.FormatoDto;
import co.com.ud.utiles.dto.FormatoIdeaDto;
import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
import co.com.ud.utiles.service.UtilesBase64;
import com.netflix.client.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class FormatoServiceImpl implements FormatoService {

    private final FormatoClient formatoClient;
    private final ArticuloCliente articuloCliente;

    private final String pathRepo;



    @Autowired
    public FormatoServiceImpl(FormatoClient formatoClient,@Value("${path.repo.articulos}")  String pathRepo, ArticuloCliente articuloCliente) {
        this.formatoClient = formatoClient;
        this.pathRepo = pathRepo;
        this.articuloCliente = articuloCliente;
    }

    @Override
    public Optional<FormatoDto> guardarFormatoArt(String token,FormatoDto formato) {
        Optional<String> name = this.generoNombreDocumento(token, formato.getIdArticulo() , formato.getFormato(), formato.getNombre());
        if(name.isEmpty()){
            return Optional.empty();
        }
        Boolean guardar = UtilesBase64.builder().build().saveFile(formato.getBase64(),pathRepo, name.get());
        if(guardar){
            //cambiamos la ruta en la que se almaceno el archivo
            formato.setUbicacion(pathRepo + name.get());
            formato.setNombre(name.get());
            Optional<FormatoDto> responseFormato = this.save(token, formato);
            if(responseFormato.isPresent()){
                //Cambiamos el estado del articulo para que el profe lo vea
                ResponseEntity<ArticuloDto> responseUpdate = articuloCliente.updateEstadoArticulo(token, formato.getIdArticulo(), "POR_REVISAR");
                return responseFormato;
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<FormatoDto> save(String token, FormatoDto formato) {
        ResponseEntity<FormatoDto> response = formatoClient.save(token, formato);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    private Optional<String> generoNombreDocumento(String token, Long idArt, TYPE_FORMATO_ARTICULO tipoFormato,  String tipoDocumento){
        String name = tipoFormato + "_" + idArt.toString() + "_art";
        //consulto si el usuario tiene mas versiones del articulo
        ResponseEntity<FormatoDto[]> response = formatoClient.getFormatoByIdArt(token, idArt);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody()) ){
            return Optional.of( name + "_" +response.getBody().length + "." + tipoDocumento );
        }else if(HttpStatus.NO_CONTENT.equals(response.getStatusCode())){
            return Optional.of( name + "." + tipoDocumento );
        }
        return Optional.empty();
    }
}
