package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articulo")
@NamedQueries({
        @NamedQuery(name = "ArticuloEntity.findByIdIdea",
                query = "from ArticuloEntity art inner join fetch art.idea as ide where ide.id = :idIdea "),
        @NamedQuery(name = "ArticuloEntity.getArticulosByUser",
                query = "from ArticuloEntity art inner join art.idea as ide inner join ide.usuario as usu where usu.id = :idUser "),
        @NamedQuery(name = "ArticuloEntity.getArticulosByTutorAndEstado",
                query = "from ArticuloEntity art inner join fetch art.idea as ide where art.estado = :estado and ide.id_profesor = :idTutor "),
        @NamedQuery(name = "ArticuloEntity.updateArticulo",
                query = "Update ArticuloEntity art  set art.resumen_ingles = :ingles, art.resumen = :resumen, art.titulo = :titulo, art.introduccion = :introduccion, art.conclusion = :conclusion where art.id = :idArt " ),
        @NamedQuery(name = "ArticuloEntity.updateUbicacionFormato",
                query = "Update ArticuloEntity art set art.ubicacion_formato = :ubFormato where art.id = :idArt "),
        @NamedQuery(name = "ArticuloEntity.updateUbicacionCartaPublicacion",
                query = "Update ArticuloEntity art set art.ubicacion_carta_publicacion = :ubCarta where art.id = :idArt "),
        @NamedQuery(name = "ArticuloEntity.conteoByEstado",
                query = "select new  co.com.ud.utiles.dto.CountStateDto( art.estado, count(art) ) from ArticuloEntity art group by art.estado")
})
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
    @Column(name="resumen_ingles" ,columnDefinition = "text")
    private String resumen_ingles;
    @Column(name="resumen" ,columnDefinition = "text")
    private String resumen;
    @Column(name="contenido")
    private String contenido;
    @Column(name="estado")
    private String estado;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_idea", nullable = false)
    private IdeaEntity idea;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articulo", orphanRemoval = true)
    private List<ControlLecturaEntity> controlLecturas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articulo", orphanRemoval = true)
    private List<ParrafoEntity> parrafos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articulo", orphanRemoval = true)
    private List<ComentarioArticuloEntity> comentarios = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articulo", orphanRemoval = true)
    private List<FormatoEntity> formatos = new ArrayList<>();

    @Column(name = "introduccion",columnDefinition = "text" )
    private String introduccion;
    @Column(name = "conclusion",columnDefinition = "text" )
    private String conclusion;
    @Column(name = "ubicacion_formato")
    private String ubicacion_formato;
    @Column(name = "ubicacion_carta_publicacion")
    private String ubicacion_carta_publicacion;

}
