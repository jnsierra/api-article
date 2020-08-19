package co.com.ud.acceptance;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ComponentScan(
        basePackages = {
                "co.com.ud.acceptance.steps"
        }
)
@PropertySource(value = "classpath:application-test.yml")
public class AcceptanceTestConfiguration {
    private ConfigurableApplicationContext context;
    private String url;

    @PostConstruct
    public void runApplication() {
        this.url = "http://localhost";
        this.context = new SpringApplicationBuilder(co.com.ud.datos.AppDatos.class)
                .profiles("test")
                .run();
    }

    @PreDestroy
    public void stopApplication() {
        if (context != null)
            context.close();
    }
}
