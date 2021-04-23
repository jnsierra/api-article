package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.FormatoIdeaCliente;
import co.com.ud.business.service.DescargaFormatoService;
import co.com.ud.utiles.dto.DocumentDownloadDto;
import co.com.ud.utiles.dto.FormatoIdeaDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DescargaFormatoServiceImpl implements DescargaFormatoService {

    private String urlFormato;
    private final FormatoIdeaCliente formatoIdeaCliente;

    @Autowired
    public DescargaFormatoServiceImpl(@Value("${repositorio.formato.ubicacion}")String urlFormato, FormatoIdeaCliente formatoIdeaCliente) {
        this.urlFormato = urlFormato;
        this.formatoIdeaCliente = formatoIdeaCliente;
    }

    @Override
    public Optional<DocumentDownloadDto> descargarFormatoIdea() {
        return obtenerDoc(urlFormato, "formato");
    }

    @Override
    public Optional<DocumentDownloadDto> descargarFormatoIdeaByIdIdea(String token, Long idIdea) {
        //Consultamos el formato
        ResponseEntity<FormatoIdeaDto[]> response = formatoIdeaCliente.getFormatoByIdIdea(token, idIdea);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            //Extraemos el ultimo registro
            List<FormatoIdeaDto> listFormato =Arrays.asList(response.getBody());
            FormatoIdeaDto item = listFormato.get(listFormato.size()-1);
            return obtenerDoc(item.getUbicacion(), item.getNombre());
        }
        return Optional.empty();
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

    private Optional<DocumentDownloadDto> obtenerDoc(String ubicacion, String nombre ){
        File file = new File(ubicacion);
        try {
            byte[] bytes = loadFile(file);
            byte[] encoded = Base64.encodeBase64(bytes);
            String encodedString = new String(encoded);
            return Optional.of( DocumentDownloadDto.builder()
                    .document(encodedString)
                    .nombre(nombre)
                    .build() );
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
