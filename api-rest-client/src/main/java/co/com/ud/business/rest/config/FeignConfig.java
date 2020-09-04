package co.com.ud.business.rest.config;

import feign.Logger;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@XSlf4j
@Configuration
@EnableFeignClients(basePackages = "co.com.ud.business.rest.client")
public class FeignConfig {

    public FeignConfig() {
        System.out.println("Llega al configurador");
        log.entry(FeignConfig.class.getName());
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
