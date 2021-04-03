package co.com.ud.business.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfigInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {
        System.out.println("********************************************* paso por el interceptor *********************************************");
        return requestTemplate -> {};
    }
}
