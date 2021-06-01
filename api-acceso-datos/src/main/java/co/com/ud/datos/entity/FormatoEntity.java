package co.com.ud.datos.entity;

import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "formato")
@NamedQueries({
        @NamedQuery(name = "FormatoEntity.findByIdArt", query = "from FormatoEntity for where for.articulo.id = :idArt ")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class FormatoEntity extends Auditable<String> {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formato_generator")
    @SequenceGenerator(name = "formato_generator", sequenceName = "formato_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "formato")
    private TYPE_FORMATO_ARTICULO formato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulo", nullable = false)
    private ArticuloEntity articulo;

}