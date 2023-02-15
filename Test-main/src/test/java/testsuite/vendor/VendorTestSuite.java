package testsuite.vendor;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = {"@feature=vendorSKUDetail"},
        features = "src/test/resources/features/vendor",
        glue = {"steps"}
)
public class VendorTestSuite {
}

