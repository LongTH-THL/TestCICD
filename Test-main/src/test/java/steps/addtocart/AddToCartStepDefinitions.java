package steps.addtocart;

import cucumber.api.java.en.And;
import cucumber.questions.CommonQuestions;
import cucumber.tasks.addtocart.AddToCart;
import cucumber.tasks.common.CommonWaitUntil;
import cucumber.tasks.common.Utility;
import cucumber.user_interface.beta.HomePageForm;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;

public class AddToCartStepDefinitions {

    @And("Search {word} by name {string} and add to cart with amount = {string}")
    public void search_by_name(String typeSearch, String value, String amount) {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.searchByValue(typeSearch, value),
                CommonWaitUntil.isVisible(HomePageForm.PRODUCT_IN_CATALOG_LABEL(value)),
                AddToCart.add(amount)
        );
    }

    @And("Verify info product in popup cart of {string}")
    public void verify_info_in_popup_cart(String product, DataTable dt) {
        List<Map<String, String>> expected = dt.asMaps(String.class, String.class);

        Utility.moveToElement("//div[@class='cart']");

        theActorInTheSpotlight().should(
                seeThat(CommonQuestions.targetText(HomePageForm.CART_COUNTER_IN_HEADER), containsString(expected.get(0).get("quantity"))),
                seeThat(CommonQuestions.isControlDisplay(HomePageForm.INFO_PRODUCT_IN_HEADER(expected.get(0).get("brand"), "brand"))),
                seeThat(CommonQuestions.isControlDisplay(HomePageForm.INFO_PRODUCT_IN_HEADER(expected.get(0).get("name"), "name"))),
                seeThat(CommonQuestions.isControlDisplay(HomePageForm.INFO_PRODUCT_IN_HEADER(expected.get(0).get("variant"), "variant"))),
                seeThat(CommonQuestions.targetText(HomePageForm.DESCRIPTION_PRODUCT_IN_HEADER(product, "case-price")), containsString(expected.get(0).get("casePrice"))),
                seeThat(CommonQuestions.targetText(HomePageForm.DESCRIPTION_PRODUCT_IN_HEADER(product, "quantity")), containsString(expected.get(0).get("quantity"))),
                seeThat(CommonQuestions.targetText(HomePageForm.TOTAL_PRODUCT_IN_HEADER(product, "total")), containsString(expected.get(0).get("total")))
        );
    }

    @And("Verify report price in popup cart")
    public void verify_report_price_in_popup_cart(String product, DataTable dt) {
        List<Map<String, String>> expected = dt.asMaps(String.class, String.class);
        // verify Total
        theActorInTheSpotlight().should(
                seeThat(CommonQuestions.targetText(HomePageForm.TOTAL_IN_HEADER), containsString(expected.get(0).get("total")))
        );

        theActorInTheSpotlight().should(
                seeThat(CommonQuestions.targetText(HomePageForm.REPORT_IN_HEADER("Order Value")), containsString(expected.get(0).get("orderValue"))),
                seeThat(CommonQuestions.targetText(HomePageForm.REPORT_IN_HEADER("Items Subtotal")), containsString(expected.get(0).get("subTotal"))),
                seeThat(CommonQuestions.targetText(HomePageForm.REPORT_IN_HEADER("Small Order Surcharge")), containsString(expected.get(0).get("smallOrderSurcharge"))),
                seeThat(CommonQuestions.targetText(HomePageForm.REPORT_IN_HEADER("Logistics Surcharge")), containsString(expected.get(0).get("logisticsSurcharge")))
        );
    }
}
