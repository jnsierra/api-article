package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "idea")
@NamedQueries({
        @NamedQuery(name = "IdeaEntity.findByUsuarioId", query = "select idea from IdeaEntity idea inner join fetch idea.usuario as usu where usu.id = :idUsua "),
        @NamedQuery(name = "IdeaEntity.findByProfesorIdAndEstado", query = "select idea from IdeaEntity idea inner join fetch idea.usuario as usu where idea.id_profesor = :idProfesor and idea.estado = :estadoIdea"),
        @NamedQuery(name = "IdeaEntity.modificarIdProfAutorizaAndEstadoAndFechaAutoriza", query = "Update IdeaEntity idea set idea.idProfesorAutoriza = :idProf, idea.estado = :estado, id.fechaAprobacion = :fechaApro where idea.id = :idIdea"),
        @NamedQuery(name = "IdeaEntity.modificarIdea", query = "update IdeaEntity idea set idea.id_profesor = :idProf, idea.estado = :estado, idea.contenido = :ideaContenido, idea.titulo = :ideaTitulo where idea.id = :idIdea")
})
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(name = "id_prof_autoriza")
    private Long idProfesorAutoriza;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_aprob")
    private Date fechaAprobacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL, mappedBy = "idea")
    private ArticuloEntity articulo;

}