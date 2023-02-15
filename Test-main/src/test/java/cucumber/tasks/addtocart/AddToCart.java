package cucumber.tasks.addtocart;

import cucumber.tasks.common.CommonWaitUntil;
import cucumber.user_interface.beta.HomePageForm;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;

public class AddToCart {
    public static Task searchByValue(String typeSearch, String value) {
        return Task.where("Tìm kiếm theo " + typeSearch,
                Check.whether(typeSearch.equals("Brands"))
                        .andIfSo(
                                // chọn brand
                        ),
                Enter.theValue(value).into(HomePageForm.SEARCH_FIELD),
                Click.on(HomePageForm.SEARCH_BUTTON)
        );
    }

    public static Task add(String amount) {
        return Task.where("Thêm vào giỏ hàng với số lượng " + amount,
                CommonWaitUntil.isVisible(HomePageForm.ADDTOCART_BUTTON),
                Click.on(HomePageForm.ADDTOCART_BUTTON),
                Enter.theValue(amount).into(HomePageForm.AMOUNT_FIELD),
                Click.on(HomePageForm.ADDTOCART_IN_POPUP_BUTTON)
        );
    }
}
