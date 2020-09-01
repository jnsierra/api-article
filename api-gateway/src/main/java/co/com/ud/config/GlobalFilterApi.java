package co.com.ud.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilterApi extends AbstractGatewayFilterFactory<GlobalFilterApi.Config> {

    public GlobalFilterApi() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //System.out.println("inside SCGWGlobalFilter.apply method");
        return (exchange, chain) -> {
            //System.out.println("Llega aquí request");
            ServerHttpRequest request = exchange.getRequest();
                    //.mutate().header("scgw-global-header", Math.random()*10+"").build();
            return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(()->{
                //System.out.println("Llega aquí response");
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                /*headers.forEach((k,v)->{
                    System.out.println(k + " : " + v);
                });*/
            }));
        } ;
    }

    public static class Config {
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
