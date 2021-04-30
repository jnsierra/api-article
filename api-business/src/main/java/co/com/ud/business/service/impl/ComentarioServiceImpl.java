package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ComentarioClient;
import co.com.ud.business.service.ComentarioService;
import co.com.ud.utiles.dto.ComentarioDto;
import co.com.ud.utiles.service.UtilesBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioClient comentarioClient;

    private final String pathComentarios;


    @Autowired
    public ComentarioServiceImpl(ComentarioClient comentarioClient,@Value("${path.repo.comentarios}") String pathComentarios) {
        this.comentarioClient = comentarioClient;
        this.pathComentarios = pathComentarios;
    }

    @Override
    public Optional<ComentarioDto> saveWithFile(String token,ComentarioDto comentarioDto) {
        String base64 = comentarioDto.getBase();
        String tipoFile = comentarioDto.getTipo_documento();
        //Persistimos el objeto en la base de datos
        Optional<ComentarioDto> response = this.persistirComentario(token, comentarioDto);
        if(response.isEmpty()){
            return Optional.empty();
        }
        if(Objects.isNull(base64) || Objects.isNull(tipoFile)){
            return response;
        }
        Boolean validaArchivo = this.persistirArchivoComentario(response.get().getId(),  base64, tipoFile, token);
        if(response.isPresent()){
            return response;
        }
        return Optional.empty();
    }


    private Optional<ComentarioDto> persistirComentario(String token, ComentarioDto comentario){
        ResponseEntity<ComentarioDto> response = comentarioClient.save(token, comentario);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    private Boolean persistirArchivoComentario(Long idComentario, String base64, String tipo, String token){
        String nombre = idComentario + "_comentario." + tipo;
        Boolean guardar = UtilesBase64.builder().build().saveFile(base64,pathComentarios, nombre);
        if(Boolean.TRUE.equals(guardar)){
            this.comentarioClient.updateUbicacionComentario(token,ComentarioDto.builder()
                    .id(idComentario)
                    .ubicacion(pathComentarios+nombre)
                    .build());
        }
        return Boolean.TRUE;
    }
}
