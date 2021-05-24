package co.com.ud.utiles.dto;

import co.com.ud.utiles.enumeracion.USER_STATE;
import co.com.ud.utiles.validators.MailConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UsuarioDto {

    private Long id;
    @MailConstraint(campo = "correo")
    private String correo;
    private String codigo;
    private String contrasena;
    private String nombre;
    private String cambioContra;
    private Integer intentos;
    private USER_STATE estado;
    private TipoUsuarioDto tipoUsuario;
    private PersonaDto persona;

}
