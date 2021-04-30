package co.com.ud.utiles.dto;

import co.com.ud.utiles.enumeracion.TYPE_COMMENTS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {

    private Long id;

    private TYPE_COMMENTS tipo_comentario;

    private Long id_usuario;

    private Long llave;

    private String comentario;

    private String base;

    private String tipo_documento;

    private String ubicacion;

}
