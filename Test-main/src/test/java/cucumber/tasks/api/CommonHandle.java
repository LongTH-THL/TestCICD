package cucumber.tasks.api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonHandle {

    // Lấy ra số tham chiếu
    public void handleSignInResponse(Response response) {
        JsonPath jsonPath = response.jsonPath();
        HashMap buyer = jsonPath.get("buyer");
        String uid = buyer.get("uid").toString();
        String email = buyer.get("email").toString();

        System.out.println("UID " + uid);
        System.out.println("EMAIL " + email);

        Serenity.recordReportData().withTitle("UID")
                .andContents(uid);
        Serenity.recordReportData().withTitle("EMAIL")
                .andContents(email);
    }
}
