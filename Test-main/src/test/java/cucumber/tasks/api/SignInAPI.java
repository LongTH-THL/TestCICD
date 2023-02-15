package cucumber.tasks.api;

import cucumber.models.User;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

public class SignInAPI {

    public Response callSignIn(User user, String basePath) {
        CommonRequest commonRequest = new CommonRequest();
        Response response = commonRequest.commonRequest(user, basePath);
        System.out.println("RESPONSE SIGN IN " + response.prettyPrint());
        Serenity.recordReportData().withTitle("RRESPONSE SIGN IN:  ").andContents(response.prettyPrint());
        return response;
    }
}
