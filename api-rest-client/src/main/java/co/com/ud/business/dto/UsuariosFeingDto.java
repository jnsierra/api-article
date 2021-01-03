package co.com.ud.business.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class UsuariosFeingDto {
    @Value("${endpoint.ms-acceso-datos.usuarios.version}")
    private String version;
    @Value("${endpoint.ms-acceso-datos.usuarios.url}")
    private String url;
}
