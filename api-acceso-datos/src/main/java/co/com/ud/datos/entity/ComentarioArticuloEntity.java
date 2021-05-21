package co.com.ud.datos.entity;

import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "comentario_articulo")
@NamedQueries({
        @NamedQuery(name = "ComentarioArticuloEntity.findByTypeComentarioArtAndArticulo",
        query = "from ComentarioArticuloEntity com inner join fetch com.articulo art where art.id = :idArt and com.typeComentarioArt = :typeCommentsArticle order by com.id desc"),
        @NamedQuery(name = "ComentarioArticuloEntity.findByArticulo",
                query = "from ComentarioArticuloEntity com inner join fetch com.articulo art where art.id = :idArt order by com.id desc")

})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class ComentarioArticuloEntity extends Auditable<String> {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_art_generator")
    @SequenceGenerator(name = "comentario_art_generator", sequenceName = "comentario_art_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comentario")
    private TYPE_COMMENTS_ARTICLE typeComentarioArt;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_articulo", nullable = false)
    private ArticuloEntity articulo;

    @Column(name = "comentario",columnDefinition = "text" )
    private String comentario;

    @Column(name = "respuesta_comentario",columnDefinition = "text" )
    private String respuestaComentario;

    @Column(name = "historico",columnDefinition = "text" )
    private String historico;

    @Column(name = "id_llave" )
    private Long llave;

}
