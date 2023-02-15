package testsuite.addtocart;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = {"@feature=addToCart"},
        features = "src/test/resources/features/addtocart",
        glue = {"steps"}
)
public class AddToCartTestSuite {
}