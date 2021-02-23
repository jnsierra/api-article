package co.com.ud.utiles.dto;

import co.com.ud.utiles.enumeracion.USER_STATE;
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
    private Integer intentos;
    private USER_STATE estado;
    private TipoUsuarioDto tipoUsuario;
    private PersonaDto persona;

}
