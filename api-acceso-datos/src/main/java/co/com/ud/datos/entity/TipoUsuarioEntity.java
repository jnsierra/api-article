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
@Table(name = "tipo_usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class TipoUsuarioEntity extends Auditable<String> {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_usuario_generator")
    @SequenceGenerator(name = "tipo_usuario_generator", sequenceName = "tipo_usuario_seq", allocationSize = 1)
    private Long id;
    @Column(name = "tipo", nullable = false)
    private String tipo;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @OneToMany(
            mappedBy = "tipoUsuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<UsuarioEntity> usuarios = new HashSet<>();


}
