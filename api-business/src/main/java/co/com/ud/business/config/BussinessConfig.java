package co.com.ud.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BussinessConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
