package co.com.ud.datos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permiso")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Audited
public class PermisoEntity extends Auditable<String>{
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permiso_generator")
    @SequenceGenerator(name = "permiso_generator", sequenceName = "permiso_seq", allocationSize = 1)
    private Long id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @ManyToMany(mappedBy = "permisos")
    private Set<RoleEntity> roles;
}
