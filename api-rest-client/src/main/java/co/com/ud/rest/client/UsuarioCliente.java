package co.com.ud.rest.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "usuario", url = "http://localhost:5003/api-datos/v.1/usuarios")
public interface UsuarioCliente {


}
