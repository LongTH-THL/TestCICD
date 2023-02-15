package cucumber.user_interface.beta;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginForm {

    public static final Target USERNAME_FIELD = Target.the("'Tên đăng nhập'").located(By.name("email"));

    public static final Target PASSWORD_FIELD = Target.the("'Mật khẩu'").located(By.name("password"));

    public static Target LOGIN_BUTTON = Target.the("'Button Log in'")
            .locatedBy("//button[contains(text(),'Sign In')]");

    public static Target SIGNIN_BUTTON = Target.the("'Button Sign in'")
            .locatedBy("//div[@class='login']");

    public static Target TYPE_CUSTOMER_RADIO(String type) {
        return Target.the("Radio I am " + type)
                .locatedBy("//input[@value='" + type + "']");
    }

    // Page admin
    public static final Target ADMIN_USERNAME_FIELD = Target.the("'Tên đăng nhập'")
            .locatedBy("//input[contains(@type,'text')]");

    public static final Target ADMIN_PASSWORD_FIELD = Target.the("'Mật khẩu'")
            .locatedBy("//input[contains(@type,'password')]");

    public static Target ADMIN_LOGIN_BUTTON = Target.the("'Button Log in'")
            .locatedBy("//button/span[contains(text(),'Login')]");

}
