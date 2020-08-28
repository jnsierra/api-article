package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuario")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id",nullable = false)
    private PersonaEntity persona;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

}
