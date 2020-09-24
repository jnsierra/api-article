package co.com.ud.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.com.ud")
public class AppBusiness {

    public static void main(String ...args){
        SpringApplication.run(AppBusiness.class);
    }
}
