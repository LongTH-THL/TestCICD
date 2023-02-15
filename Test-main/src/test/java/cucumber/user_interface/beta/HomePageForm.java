package cucumber.user_interface.beta;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePageForm {
    // Header
    public static Target CART_IN_HEADER = Target.the("'Button Add to cart'")
            .locatedBy("//div[@class='cart']");

    public static Target CART_COUNTER_IN_HEADER = Target.the("'Label counter of cart'")
            .locatedBy("//div[contains(@class,'cart')]/span[@class='counter']");

    public static Target TOTAL_IN_HEADER = Target.the("'Label total of cart'")
            .locatedBy("//div[@class='cart  active']/span[contains(@class,'caption')]");

    // DIALOG CART IN HEADER
    public static Target INFO_PRODUCT_IN_HEADER(String nameProduct, String type) {
        return Target.the("Info " + type + " of " + nameProduct)
                .locatedBy("//div[text()='" + nameProduct + "' and @class='" + type + "']");
    }

    public static Target DESCRIPTION_PRODUCT_IN_HEADER(String nameProduct, String type) {
        return Target.the("Case price of " + nameProduct)
                .locatedBy("//div[text()='" + nameProduct + "']/parent::div/following-sibling::div//span[@class='" + type + "']");
    }

    public static Target TOTAL_PRODUCT_IN_HEADER(String nameProduct, String type) {
        return Target.the("Total of " + nameProduct)
                .locatedBy("//div[text()='" + nameProduct + "']/parent::div/following-sibling::div//div[@class='" + type + "']");
    }

    public static Target REPORT_IN_HEADER(String title) {
        return Target.the("Report price of " + title)
                .locatedBy("//div[@class='cart-popper']//td[text()='" + title + "']/following-sibling::td");
    }

    //div[text()='Product exam1']/parent::div/following-sibling::div//span[@class='case-price']
    // Pop up
    public static Target CLOSE_POPUP_BUTTON = Target.the("'Button Close Popup'")
            .locatedBy("//div[contains(@class,'buyer-app-launching-banner')]//div[@class='close-btn']");

    // Search Bar
    public static final Target SEARCH_FIELD = Target.the("'Textbox Search '").located(By.name("query"));

    public static final Target SEARCH_BUTTON = Target.the("'Button Search '")
            .locatedBy("//input[@name='query']/following-sibling::button");

    public static Target SIGNIN_BUTTON = Target.the("'Button Sign in'")
            .locatedBy("//div[@class='login']");

    public static Target ADDTOCART_BUTTON = Target.the("'Button Add to cart'")
            .locatedBy("//span[@data-tip='Add to cart']");

    public static Target PRODUCT_IN_CATALOG_LABEL(String nameProduct) {
        return Target.the("Radio I am " + nameProduct)
                .locatedBy("(//a[contains(text(),'" + nameProduct + "')])[1]");
    }

    // POPUP ADD TO CART
    public static Target AMOUNT_FIELD = Target.the("'Textbox amount")
            .locatedBy("//input[@name='cart_items[0][quantity]']");

    public static Target ADDTOCART_IN_POPUP_BUTTON = Target.the("'Button Add to cart in popup'")
            .locatedBy("//button[text()='Add to cart']");
////a[text()='Product exam1']/parent::div/following-sibling::div

}
