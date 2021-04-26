package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControlLecturaDto {

    private Long id;


    private Long idArticulo;

    private Long orden;

    private String estado;

    private String link;

    private String autor;

    private String comentario;

    private Long year;
}
