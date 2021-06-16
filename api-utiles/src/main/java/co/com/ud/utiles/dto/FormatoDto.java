package co.com.ud.utiles.dto;

import co.com.ud.utiles.enumeracion.TYPE_FORMATO_ARTICULO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormatoDto {

    private Long id;

    private String ubicacion;

    private String estado;

    private String nombre;

    private TYPE_FORMATO_ARTICULO formato;

    private Long idArticulo;

    private String base64;

    private String base64FormatoBase;

    private String mensaje;
}
