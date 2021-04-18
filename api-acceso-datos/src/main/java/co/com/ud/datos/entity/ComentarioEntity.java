package co.com.ud.datos.entity;

import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
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
}
