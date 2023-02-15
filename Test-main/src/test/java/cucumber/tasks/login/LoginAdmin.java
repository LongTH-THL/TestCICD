package cucumber.tasks.login;

import cucumber.models.User;
import cucumber.tasks.common.CommonWaitUntil;
import cucumber.user_interface.beta.LoginForm;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;


public class LoginAdmin {

    public static Task as( User user) {
        return Task.where("Tìm kiếm theo ",
                CommonWaitUntil.isVisible(LoginForm.ADMIN_USERNAME_FIELD),
                Enter.theValue(user.getEmail())
                        .into(LoginForm.ADMIN_USERNAME_FIELD),
                Enter.theValue(user.getPassword())
                        .into(LoginForm.ADMIN_PASSWORD_FIELD),
                Click.on(LoginForm.ADMIN_LOGIN_BUTTON)
                );
    }

    public static Task as1( User user, String role) {
        return Task.where("Tìm kiếm theo ",
                CommonWaitUntil.isVisible(LoginForm.SIGNIN_BUTTON),
                Click.on(LoginForm.SIGNIN_BUTTON),
                Enter.theValue(user.getEmail())
                        .into(LoginForm.USERNAME_FIELD),
                Enter.theValue(user.getPassword())
                        .into(LoginForm.PASSWORD_FIELD),
                Check.whether(role.equals("Buyer"))
                        .andIfSo(
                                Click.on(LoginForm.TYPE_CUSTOMER_RADIO(role)))
                        .otherwise(
                                Click.on(LoginForm.TYPE_CUSTOMER_RADIO(role))),
                Click.on(LoginForm.LOGIN_BUTTON)
        );
    }
}
