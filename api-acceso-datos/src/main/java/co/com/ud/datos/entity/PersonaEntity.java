package co.com.ud.datos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Persona")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_generator")
    @SequenceGenerator(name = "persona_generator", sequenceName = "persona_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL, mappedBy = "persona")
    private UsuarioEntity usuario;

    @Column(name = "nombres",nullable = false)
    private String nombres;

    @Column(name = "apellidos",nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento",nullable = false)
    private Date fechaNacimiento;
}
