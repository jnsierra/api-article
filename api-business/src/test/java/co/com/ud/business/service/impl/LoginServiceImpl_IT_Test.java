package co.com.ud.business.service.impl;

import co.com.ud.business.dto.FeingParamsDto;
import co.com.ud.utiles.enumeracion.LOGIN_ACTION;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class LoginServiceImpl_IT_Test {

    @Autowired
    private LoginServiceImpl loginServiceImpl;
    @Getter
    @Autowired
    private FeingParamsDto feingParamsDto;


    public WireMockServer wireMockServer;

    @Before
    public void setUp(){
        this.wireMockServer = new WireMockServer(options().port(feingParamsDto.getMsAccesoDatosDTO().getPort())
                .bindAddress(feingParamsDto.getMsAccesoDatosDTO().getHost()));

    }

    @Test
    public void testLoginSUCCESS() {
        wireMockServer.start();
        configureFor(feingParamsDto.getMsAccesoDatosDTO().getHost(), feingParamsDto.getMsAccesoDatosDTO().getPort());
        stubFor(get(urlEqualTo("/api-datos/v.1/usuarios/by/?correo=jnsierrac%40gmail.com"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withBodyFile("responseUsers.json")));
        stubFor(get(urlEqualTo("/api-datos/v.1/usuarios/by/?correo=jnsierrac%40gmail.com&contrasenia=123456"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("responseUsers.json")));
        LOGIN_ACTION rta = loginServiceImpl.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.SUCCESS, rta );
        wireMockServer.stop();
    }
    @Test
    public void testLoginFAILED(){
        wireMockServer.start();
        configureFor(feingParamsDto.getMsAccesoDatosDTO().getHost(), feingParamsDto.getMsAccesoDatosDTO().getPort());
        stubFor(get(urlEqualTo("/api-datos/v.1/usuarios/by/?correo=jnsierrac%40gmail.com"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withBodyFile("responseUsers.json")));

        stubFor(get(urlEqualTo("/api-datos/v.1/usuarios/by/?correo=jnsierrac%40gmail.com&contrasenia=123456"))
                .willReturn(aResponse().withStatus(204)));

        stubFor(put(urlEqualTo("/api-datos/v.1/usuarios/jnsierrac%40gmail.com/intentos"))
                .willReturn(aResponse().withStatus(200)));

        LOGIN_ACTION rta = loginServiceImpl.validaLogin("jnsierrac@gmail.com", "123456");
        Assert.assertNotNull(rta);
        Assert.assertEquals(LOGIN_ACTION.PASSWORD_INCORRECT, rta );
        wireMockServer.stop();
    }
}