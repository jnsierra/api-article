package co.com.ud.utiles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogRequestDto {

    private Long id;
    private String url;
    private String traza;
    private String ususario;

}
