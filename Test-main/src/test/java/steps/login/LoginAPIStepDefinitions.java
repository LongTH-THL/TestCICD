package steps.login;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.models.User;
import cucumber.singleton.BasePathAPI;
import cucumber.singleton.GVs;
import cucumber.tasks.api.CommonHandle;
import cucumber.tasks.api.SignInAPI;
import cucumber.tasks.common.CommonTask;
import io.restassured.response.Response;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;

public class LoginAPIStepDefinitions {

    public static EnvironmentVariables env;
    SignInAPI signInAPI = new SignInAPI();
    CommonHandle commonHandle = new CommonHandle();

    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{word} login to web with role {string} with API")
    public void signInAPI(String name, String role) {
        User user = CommonTask.getUser(name);
        if (role.equals("buyer")) {
            GVs.ENVIRONMENT_BASEURI = EnvironmentSpecificConfiguration.from(env).getProperty("environments.default.apiBuyer");
        } else {
            GVs.ENVIRONMENT_BASEURI = EnvironmentSpecificConfiguration.from(env).getProperty("environments.default.apiVendor");
        }
        String basePath = BasePathAPI.SIGNIN;
        System.out.println("basePath " + GVs.ENVIRONMENT_BASEURI + BasePathAPI.SIGNIN);

        Response response = signInAPI.callSignIn(user, basePath);
        commonHandle.handleSignInResponse(response);

    }
}
