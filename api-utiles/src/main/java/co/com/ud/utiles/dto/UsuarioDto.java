package co.com.ud.utiles.dto;

import lombok.Data;

@Data
public class UsuarioDto {

    private Long id;
    private String correo;
    private String contrasena;
    private String nombre;
    private String cambioContra;

}
