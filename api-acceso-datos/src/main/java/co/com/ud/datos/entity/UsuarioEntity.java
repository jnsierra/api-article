package co.com.ud.datos.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
    @SequenceGenerator(name = "usuario_generator", sequenceName = "usuario_seq", allocationSize = 1)
    private Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "email")
    private String correo;
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "password")
    private String contrasena;
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "name")
    private String nombre;
    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "cambio_contra")
    private String cambioContra;

}
