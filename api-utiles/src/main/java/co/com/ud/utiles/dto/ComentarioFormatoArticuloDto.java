package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioFormatoArticuloDto {

    private Long id;

    private String tipoComentario;

    private Long idUsuario;

    private String comentario;

    private String respuestaAlumno;

    private String ubicacion;

    private Long idFormato;

}
