package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.FormatoIdeaCliente;
import co.com.ud.business.service.FormatoIdeaService;
import co.com.ud.business.service.IdeaService;
import co.com.ud.utiles.dto.FormatoIdeaDto;
import co.com.ud.utiles.service.UtilesBase64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormatoIdeaServiceImpl implements FormatoIdeaService {

    private final FormatoIdeaCliente formatoIdeaCliente;
    private final IdeaService ideaService;
    private String pathRepo;

    private static final Logger logger = LogManager.getLogger(FormatoIdeaServiceImpl.class);

    @Autowired
    public FormatoIdeaServiceImpl(FormatoIdeaCliente formatoIdeaCliente,@Value("${path.repo.idea}")  String pathRepo, IdeaService ideaService) {
        this.formatoIdeaCliente = formatoIdeaCliente;
        this.pathRepo = pathRepo;
        this.ideaService = ideaService;
    }

    @Override
    public Optional<FormatoIdeaDto> saveFormato(String token,FormatoIdeaDto formato) {
        ResponseEntity<FormatoIdeaDto> formatoIdea = formatoIdeaCliente.save(token,formato);
        if(HttpStatus.OK.equals(formatoIdea.getStatusCode())){
            return Optional.of(formatoIdea.getBody());
        }
        return Optional.empty();
    }

    @Override
    public Optional<FormatoIdeaDto> persistirFormatoIdea(String token, FormatoIdeaDto formatoIdea) {
        //Guardo el archivo en el repo
        Optional<String> name = this.generoNombreDocumento(token, formatoIdea.getIdIdea(), formatoIdea.getFormato(), formatoIdea.getTipo());
        if(name.isEmpty()){
            logger.error("ERROR_SAVE_FORMATO|{}|{}|{}","IDEA",formatoIdea.getIdIdea() , formatoIdea.getFormato());
            return Optional.empty();
        }
        Boolean guardar = UtilesBase64.builder().build().saveFile(formatoIdea.getBase64(),pathRepo, name.get());
        if(guardar){
            //cambiamos la ruta en la que se almaceno el archivo
            formatoIdea.setUbicacion(pathRepo + name);
            formatoIdea.setNombre(name.get());
            Optional<FormatoIdeaDto> formato = saveFormato(token,formatoIdea);
            if(formato.isPresent()){
                //Actualiza el estado del idea
                ideaService.updateStatus(token, formatoIdea.getIdIdea(),"POR_CONFIRMAR_FORMATO");
            }
            return formato;
        }
        return Optional.empty();
    }

    private Optional<String> generoNombreDocumento(String token,  Long idIdea, String tipoFormato, String tipoDocumento){
        String name = tipoFormato + "_" + idIdea.toString() + "_idea";
        //consulto si el usuario tiene mas versiones del docuemento
        ResponseEntity<FormatoIdeaDto[]> response = formatoIdeaCliente.getFormatoByIdIdea(token,idIdea);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return Optional.of( name + "_" +response.getBody().length + "." + tipoDocumento );
        }else if(HttpStatus.NO_CONTENT.equals(response.getStatusCode())){
            return Optional.of( name + "." + tipoDocumento );
        }
        return Optional.empty();
    }

}