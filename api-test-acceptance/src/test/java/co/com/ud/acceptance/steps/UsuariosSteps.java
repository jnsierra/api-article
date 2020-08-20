package co.com.ud.acceptance.steps;

import co.com.ud.acceptance.worlds.World;
import co.com.ud.utiles.dto.UsuarioDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

public class UsuariosSteps extends StepRoot {
    private final World world;
    private final CommonSteps commonSteps;
    private UsuarioDto user;

    public UsuariosSteps(World world, CommonSteps commonSteps) {
        this.world = world;
        this.commonSteps = commonSteps;
        this.commonSteps.setWorld(world);
    }

    @Given("los siguientes datos para crear usuario")
    public void losSiguientesDatosParaCrearUsuario(List<UsuarioDto> userInputList) {
        user = userInputList.get(0);
    }

    @When("se llama a crear usuario")
    public void crearUsuario() {
        final WebTestClient.ResponseSpec responseSpec = this.webTestClient
                .post()
                .uri("/v.1/usuarios/")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user))
                .exchange();
    }
}
