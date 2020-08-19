package co.com.ud.acceptance.steps;

import co.com.ud.acceptance.AcceptanceTestConfiguration;
import co.com.ud.acceptance.worlds.World;
import io.cucumber.java.en.Then;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AcceptanceTestConfiguration.class)
public class CommonSteps extends StepRoot {
    private World world;

    public void setWorld(World world) {
        this.world = world;
    }

    @Then("a \"([^\"]*)\"response is returned")
    public void aResponseIsReturned(int status) {
        final WebTestClient.ResponseSpec responseSpec = this.world.getResponseSpec();
        responseSpec.expectStatus().isEqualTo(status);
    }
}
