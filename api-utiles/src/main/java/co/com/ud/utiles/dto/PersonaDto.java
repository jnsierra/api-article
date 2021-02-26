package co.com.ud.utiles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {

    private Long id;

    private String nombres;

    private String apellidos;

    private Date fechaNacimiento;

    private String documento;
}
