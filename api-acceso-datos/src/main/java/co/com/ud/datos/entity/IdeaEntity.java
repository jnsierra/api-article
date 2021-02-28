package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "idea")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class IdeaEntity extends Auditable<String>{

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idea_generator")
    @SequenceGenerator(name = "idea_generator", sequenceName = "idea_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @Column(name="id_profesor")
    private Long id_profesor;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "estado")
    private String estado;

    @Column(name = "titulo")
    private String titulo;

}