package co.com.ud.utiles.dto;

import co.com.ud.utiles.enumeracion.TYPE_COMMENTS_ARTICLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioArticuloDto {

    private Long id;

    private Long idArticulo;

    private TYPE_COMMENTS_ARTICLE typeComentarioArt;

    private Long idUsuario;

    private String comentario;

    private String respuestaComentario;

    private String historico;

    private Long llave;
}
