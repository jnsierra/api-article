package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfesoresIdeaDto {

    private Long idIdea;

    private Long idProAsig;

    private String nombreProAsig;

    private Long idProfTutor;

    private String nombreProTutor;

    private Long idProfJurado;

    private String nombreProJurado;

}
