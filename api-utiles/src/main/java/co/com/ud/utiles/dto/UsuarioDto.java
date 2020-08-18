package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String correo;
    private String contrasena;
    private String nombre;
    private String cambioContra;

}
