package co.com.ud.business.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeingParamsDto {
    @Autowired
    @Getter @Setter
    private MsAccesoDatosDto msAccesoDatosDTO;
}
