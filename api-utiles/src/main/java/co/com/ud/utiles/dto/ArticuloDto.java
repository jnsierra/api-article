package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDto {

    private Long id;

    private String titulo;

    private String resumen_ingles;

    private String resumen;

    private String contenido;

    private String estado;

    private Long ideaId;

    private String introduccion;

    private String conclusion;

    private String ubicacion_formato;

}
