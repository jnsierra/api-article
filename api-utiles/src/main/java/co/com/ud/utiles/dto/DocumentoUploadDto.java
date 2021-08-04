package co.com.ud.utiles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentoUploadDto {

    private String nombre;
    private String extencion;
    private String base64;
    private String ubicacion;
}
