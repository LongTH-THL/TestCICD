package steps.login;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.models.User;
import cucumber.singleton.GVs;

import cucumber.tasks.common.CommonTask;
import cucumber.tasks.common.CommonWaitUntil;
import cucumber.tasks.common.NavigateToSideBar;
import cucumber.tasks.login.LoginAdmin;
import cucumber.tasks.login.Start;
import cucumber.user_interface.admin.SideBar;
import cucumber.user_interface.beta.HomePageForm;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.conditions.Check;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    public static EnvironmentVariables env;

    @Before()
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
        String enviroment = null;
        if (System.getProperty("environment") == null) {
            enviroment = "default";
        } else {
            enviroment = System.getProperty("environment");
        }
        GVs.ENVIRONMENT = enviroment;
    }

    /* LOGIN STEP DEFINITIONS */
    @Given("{word} open web")
    public void open_web(String actor) {
        theActorCalled(actor).wasAbleTo(Start.start());
    }

    @Given("{word} login to web with role {word}")
    public void login_to_web(String name, String role) {
        User user = CommonTask.getUser(name);

        theActorInTheSpotlight().attemptsTo(
                Check.whether(role.equals("Admin"))
                        .andIfSo(
                                LoginAdmin.as(user))
                        .otherwise(
                                LoginAdmin.as(user),
                                CommonWaitUntil.isVisible(HomePageForm.CLOSE_POPUP_BUTTON),
                                Click.on(HomePageForm.CLOSE_POPUP_BUTTON))

        );
    }

    @Given("Navigate to {string} to {string} by sidebar")
    public void navigateTo(String parentName, String childName) {
        theActorInTheSpotlight().attemptsTo(
                NavigateToSideBar.navigate(null, SideBar.PARENT_MENU(parentName), SideBar.CHILD_MENU(childName))
        );

    }
}
