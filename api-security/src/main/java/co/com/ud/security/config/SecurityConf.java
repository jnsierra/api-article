package co.com.ud.security.config;

import co.com.ud.security.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("******************************** Inicio la configuracion de seguridad ********************************");
        http.csrf().disable()
                .addFilterAfter(getJwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login/","/v.1/usuarios/**")
                .permitAll()
                .antMatchers( "/v.1/usuarios/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/v.1/personas/")
                .permitAll()
                .anyRequest()
                .authenticated();
        //http.authorizeRequests().antMatchers("/v.1/usuarios/").permitAll().anyRequest().authenticated();
    }

    @Bean
    public JwtFilter getJwtFilter(){
        return new JwtFilter(secret);
    }
}
