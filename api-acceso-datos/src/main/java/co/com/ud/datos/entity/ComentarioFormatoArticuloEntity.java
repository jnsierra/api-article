package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "comentario_formato_articulo")
@NamedQueries({
        @NamedQuery(name = "ComentarioFormatoArticuloEntity.getByFormato"
                , query = "from ComentarioFormatoArticuloEntity comFor inner join fetch comFor.formato formato WHERE formato.id = :idFormato ")
})
@Audited
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioFormatoArticuloEntity extends Auditable<String> {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_formato_articulo_generator")
    @SequenceGenerator(name = "comentario_formato_articulo_generator", sequenceName = "comentario_formato_articulo_seq", allocationSize = 1)
    private Long id;
    @Column(name = "tipo_comentario")
    private String tipoComentario;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formato", nullable = false)
    private FormatoEntity formato;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "respuesta_alumno")
    private String respuestaAlumno;
    @Column(name = "ubicacion")
    private String ubicacion;

}
