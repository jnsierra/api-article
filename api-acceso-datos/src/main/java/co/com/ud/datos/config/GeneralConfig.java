package co.com.ud.datos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
