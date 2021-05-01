package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormatoIdeaDto {

    private Long id;

    private Long idIdea;

    private String ubicacion;

    private String nombre;

    private String formato;

    private String base64;

    private String tipo;

}
