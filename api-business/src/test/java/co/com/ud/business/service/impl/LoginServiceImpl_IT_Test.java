package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.UsuarioCliente;
import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class LoginServiceImpl_IT_Test {

    @Autowired
    private LoginServiceImpl loginServiceImpl;


    public WireMockServer wireMockServer = new WireMockServer(5003);

    @Test
    public void testUserSuccess() {
        wireMockServer.start();
        configureFor("localhost", 5003);
        stubFor(get(urlEqualTo("/api-datos/v.1/usuarios/by/?correo=jnsierrac"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withBodyFile("responseUsers.json")));
        stubFor(get(urlEqualTo("/api-datos/v.1/usuarios/by/?correo=jnsierrac&contrasenia=123456"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withBodyFile("responseUsers.json")));
        LOGIN_ACTION rta = loginServiceImpl.validaLogin("jnsierrac", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.SUCCESS, rta );
        wireMockServer.stop();
    }
}