package co.com.ud.acceptance;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = {"classpath:features"},
        plugin = {"pretty:","html:target/cucumber-reports", "json:target/cucumber-reports/cucumber.json"},
        glue = {"co.com.ud.acceptance.steps"}
)

@RunWith(CucumberWithSerenity.class)
public class AcceptanceIT {
}
