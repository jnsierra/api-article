package co.com.ud.utiles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDownloadDto {

    private String document;
    private String nombre;
}
