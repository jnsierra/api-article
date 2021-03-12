package co.com.ud.utiles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionValidatorDto {

    private String mensaje;
    private String tipoError;
    private String campo;
}
