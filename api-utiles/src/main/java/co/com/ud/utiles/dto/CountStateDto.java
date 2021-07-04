package co.com.ud.utiles.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CountStateDto {

    private String estado;
    private Long conteo;

    public CountStateDto(String estado, Long conteo) {
        this.estado = estado;
        this.conteo = conteo;
    }
}
