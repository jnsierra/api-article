package co.com.ud.datos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PermisoEntity> permisos = new HashSet<>();


}
