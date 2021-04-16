package co.com.ud.utiles.service;

import lombok.Builder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;

@Builder
public class UtilesBase64 {

    public Boolean saveFile(String base64, String directoryPath, String nameFile){
        Path path = Paths.get(directoryPath);
        boolean isDir = Files.isDirectory(path);
        if(!isDir){
            return Boolean.FALSE;
        }

        try {
            base64 = base64.replace("data:application/pdf;base64,", "");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedByteArray = decoder.decode(base64);
            File file = new File(directoryPath + nameFile );

            OutputStream os = new FileOutputStream(file);
            os.write(decodedByteArray);
            os.close();
        } catch (Exception e) {
            //e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
