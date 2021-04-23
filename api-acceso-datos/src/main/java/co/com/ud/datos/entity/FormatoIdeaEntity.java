package co.com.ud.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "formato_idea")
@NamedQueries({
        @NamedQuery(name="FormatoIdeaEntity.getFormatosByIdea", query = "from FormatoIdeaEntity formato where formato.idea.id = :idIdea order by formato.id desc ")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class FormatoIdeaEntity extends Auditable<String> {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formato_idea_generator")
    @SequenceGenerator(name = "formato_idea_generator", sequenceName = "formato_idea_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_idea", nullable = false)
    private IdeaEntity idea;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "formato")
    private String formato;

}
