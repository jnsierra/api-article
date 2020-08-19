package co.com.ud.acceptance;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.netty.http.client.HttpClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;

@ComponentScan(
        basePackages = {
                "co.com.ud.acceptance.steps",
                "co.com.ud.acceptance.worlds"
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

    @Bean
    public WebTestClient webTestClient(final Environment environment) {
        final String port = environment.getProperty("server.port");
        final String path = environment.getProperty("server.servlet.context-path");
        final ReactorClientHttpConnector connector = new ReactorClientHttpConnector(HttpClient.create());
        return WebTestClient.bindToServer(connector)
                .baseUrl(url.concat(port).concat(path)).responseTimeout(Duration.ofMillis(15000))
                .build();
    }
}
