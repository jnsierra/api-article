package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParrafoDto {

    private Long id;

    private Long orden;

    private String estado;

    private String contenido;

    private Long idArticulo;

}
