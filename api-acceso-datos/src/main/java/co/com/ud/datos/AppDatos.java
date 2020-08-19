package co.com.ud.datos;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppDatos {

    public static void main(String... args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AppDatos.class);
        LoggerFactory.getLogger(AppDatos.class).info("Los beans son {} ", applicationContext.getBeanDefinitionNames().length);
    }
}
