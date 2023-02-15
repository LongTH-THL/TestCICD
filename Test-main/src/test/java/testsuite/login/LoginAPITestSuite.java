package testsuite.login;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = {"@feature=Login-API"},
        features = "src/test/resources/features/login",
        glue = {"steps"}
)
public class LoginAPITestSuite {
}