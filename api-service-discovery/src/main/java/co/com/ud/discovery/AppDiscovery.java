package co.com.ud.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppDiscovery {

    public static void main(String ... args){
        SpringApplication.run(AppDiscovery.class, args);
    }

}
