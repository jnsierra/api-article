package co.com.ud.business.service.impl;

import co.com.ud.business.service.DescargaFormatoService;
import co.com.ud.utiles.dto.DocumentDownloadDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class DescargaFormatoServiceImpl implements DescargaFormatoService {

    private String urlFormato;

    @Autowired
    public DescargaFormatoServiceImpl(@Value("${repositorio.formato.ubicacion}")String urlFormato) {
        this.urlFormato = urlFormato;
    }

    @Override
    public Optional<DocumentDownloadDto> descargarFormatoIdea() {
        File file = new File(urlFormato);
        try {
            byte[] bytes = loadFile(file);
            byte[] encoded = Base64.encodeBase64(bytes);
            String encodedString = new String(encoded);
            return Optional.of( DocumentDownloadDto.builder()
                    .document(encodedString)
                    .nombre("formato")
                    .build() );
        }catch (Exception e){
            return Optional.empty();
        }
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
}
