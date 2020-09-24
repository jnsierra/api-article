package co.com.ud.datos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class RoleEntity extends Auditable<String>{
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Set<UsuarioEntity> usuarios = new HashSet<>();

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_permiso", joinColumns = @JoinColumn(name = "role_id"),inverseJoinColumns = @JoinColumn(name = "permiso_id"))
    private Set<PermisoEntity> permisos = new HashSet<>();


}
