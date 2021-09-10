package co.com.ud.business.rest.client;

import co.com.ud.utiles.dto.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${endpoint.ms-acceso-mail.mail.name}",
        url = "${endpoint.ms-acceso-mail.protocol}${endpoint.ms-acceso-mail.host}:${endpoint.ms-acceso-mail.port}${endpoint.ms-acceso-mail.base}${endpoint.ms-acceso-mail.mail.version}${endpoint.ms-acceso-mail.mail.url}")
public interface MailCliente {

    @PostMapping(value = "/send/")
    ResponseEntity<Boolean> enviarMail(@RequestHeader("Authorization") String token,@RequestBody EmailDto emailDto);

}
