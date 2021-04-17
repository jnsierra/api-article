package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.FormatoIdeaCliente;
import co.com.ud.business.service.FormatoIdeaService;
import co.com.ud.utiles.dto.FormatoIdeaDto;
import co.com.ud.utiles.service.UtilesBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormatoIdeaServiceImpl implements FormatoIdeaService {

    private final FormatoIdeaCliente formatoIdeaCliente;
    private String pathRepo;

    @Autowired
    public FormatoIdeaServiceImpl(FormatoIdeaCliente formatoIdeaCliente,@Value("${path.repo.idea}")  String pathRepo) {
        this.formatoIdeaCliente = formatoIdeaCliente;
        this.pathRepo = pathRepo;
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
        String name = formatoIdea.getIdIdea() + "_idea.pdf";
        Boolean guardar = UtilesBase64.builder().build().saveFile(formatoIdea.getBase64(),pathRepo, name);
        if(guardar){
            //cambiamos la ruta en la que se almaceno el archivo
            formatoIdea.setUbicacion(pathRepo + name);
            formatoIdea.setNombre(name);
            return saveFormato(token,formatoIdea);
        }
        return Optional.empty();
    }

}