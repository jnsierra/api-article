package co.com.ud.business.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class MsAccesoDatosDto {
    @Value("${endpoint.ms-acceso-datos.protocol}")
    private String protocol;
    @Value("${endpoint.ms-acceso-datos.host}")
    private String host;
    @Value("${endpoint.ms-acceso-datos.port}")
    private Integer port;
    @Value("${endpoint.ms-acceso-datos.base}")
    private String base;
    @Autowired
    private UsuariosFeingDto usuariosFeingDto;
}
