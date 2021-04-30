package co.com.ud.datos.entity;

import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
@NamedQueries({
        @NamedQuery(name = "ComentarioEntity.findByLlaveAndTipoComentario"
                , query = "from ComentarioEntity com WHERE com.llave = :llave and com.tipo_comentario = :tipoComentario order by id desc"),
        @NamedQuery(name = "ComentarioEntity.updateComentarioUbicacion"
                , query = "Update ComentarioEntity com set com.ubicacion = :ubicacion where com.id = :idCom")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioEntity extends Auditable<String>{
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_generator")
    @SequenceGenerator(name = "comentario_generator", sequenceName = "comentario_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_comentario")
    private TYPE_COMMENTS tipo_comentario;
    @Column(name = "id_usuario")
    private Long id_usuario;
    @Column(name = "llave")
    private Long llave;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "ubicacion")
    private String ubicacion;
}
