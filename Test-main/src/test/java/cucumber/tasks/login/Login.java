package cucumber.tasks.login;

import cucumber.models.User;
import cucumber.tasks.common.CommonWaitUntil;
import cucumber.user_interface.beta.LoginForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class Login implements Task {

    User user;
    private EnvironmentVariables env;
    String role;

    @Step("{0} đăng nhập")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
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

    /**
     * Hàm thực hiện task đăng nhập vào hệ thống
     *
     * @param user Thông tin đăng nhập
     * @param role Vai trò
     */
    public static Login as(User user, String role) {
        return instrumented(Login.class, user, role);
    }

    public Login(User user, String role) {
        this.user = user;
        this.role = role;
    }

}
