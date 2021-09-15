package co.com.ud.datos.entity;

import co.com.ud.utiles.enumeracion.USER_STATE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@NamedQueries({
        @NamedQuery(name = "UsuarioEntity.updateIntentos",
                query = "update UsuarioEntity usu set usu.intentos = :intentos WHERE usu.id = :id"),
        @NamedQuery(name = "UsuarioEntity.inactivarUsuario",
                query = "update UsuarioEntity usu set usu.estado = 'INACTIVO' WHERE usu.id = :id "),
        @NamedQuery(name = "UsuarioEntity.modificarEstadoUsuario",
                query = "update UsuarioEntity usu set usu.estado = :estado where usu.id = :id "),
        @NamedQuery(name = "UsuarioEntity.modificarPasswordTeporal",
                query = "update UsuarioEntity usu set usu.contrasena = :contra, usu.cambioContra = :cambioContra where usu.correo = :correo "),
        @NamedQuery(name = "UsuarioEntity.findByTipoUsuario",
                query = "from UsuarioEntity usuario inner join usuario.tipoUsuario as tipoU WHERE tipoU.tipo = :tipoUsuario")
})
public class UsuarioEntity extends Auditable<String> {

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
    @Column(name = "codigo")
    private String codigo;

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
    @JoinColumn(name = "persona_id", nullable = false)
    private PersonaEntity persona;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private TipoUsuarioEntity tipoUsuario;

    @Column(name = "intentos")
    private Integer intentos;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private USER_STATE estado;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
    private List<IdeaEntity> ideas = new ArrayList<IdeaEntity>();

    public void addIdea(IdeaEntity idea) {
        ideas.add(idea);
        idea.setUsuario(this);
    }

    public void removeIdea(IdeaEntity idea) {
        ideas.remove(idea);
        idea.setUsuario(null);
    }

}
