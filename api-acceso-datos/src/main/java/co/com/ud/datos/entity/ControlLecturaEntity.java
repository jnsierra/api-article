package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "control_lectura")
@NamedQueries({
        @NamedQuery(name = "ControlLecturaEntity.getByIdArticulo",
                query = "from ControlLecturaEntity as contLec inner join contLec.articulo as art where art.id = :idArt ")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class ControlLecturaEntity extends Auditable<String>{

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "control_lectura_generator")
    @SequenceGenerator(name = "control_lectura_generator", sequenceName = "control_lectura_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulo", nullable = false)
    private ArticuloEntity articulo;

    @Column(name = "orden")
    private Long orden;

    @Column(name = "estado")
    private String estado;

    @Column(name = "link")
    private String link;

    @Column(name = "autor")
    private String autor;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "year")
    private Long year;

}
