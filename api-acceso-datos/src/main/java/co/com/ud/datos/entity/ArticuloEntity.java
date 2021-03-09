package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "articulo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class ArticuloEntity extends Auditable<String>{
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "articulo_generator")
    @SequenceGenerator(name = "articulo_generator", sequenceName = "articulo_seq", allocationSize = 1)
    private Long id;
    @Column(name="titulo")
    private String titulo;
    @Column(name="resumen_ingles")
    private String resumen_ingles;
    @Column(name="resumen")
    private String resumen;
    @Column(name="contenido")
    private String contenido;
    @Column(name="estado")
    private String estado;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_idea", nullable = false)
    private IdeaEntity idea;
}
