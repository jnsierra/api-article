package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "parrafo")
@NamedQueries({
        @NamedQuery(name = "ParrafoEntity.getParrafoByArticulo", query = "from ParrafoEntity parr inner join parr.articulo art WHERE art.id = :artId")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class ParrafoEntity extends Auditable<String>{

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parrafo_generator")
    @SequenceGenerator(name = "parrafo_generator", sequenceName = "parrafo_seq", allocationSize = 1)
    private Long id;
    @Column(name = "orden")
    private Long orden;
    @Column(name = "estado")
    private String estado;
    @Column(name = "contenido")
    private String contenido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulo", nullable = false)
    private ArticuloEntity articulo;

}
