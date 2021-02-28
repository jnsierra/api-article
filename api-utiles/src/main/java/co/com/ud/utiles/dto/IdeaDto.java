package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdeaDto {

    private Long id;

    private Long usuarioId;

    private Long id_profesor;

    private String contenido;

    private String estado;

    private String titulo;

    private String nombreProf;
}
