package cucumber.tasks.api;

import cucumber.singleton.GVs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommonRequest {

    public Response commonRequest(Object modelApi, String url) {

        RestAssured.baseURI = GVs.ENVIRONMENT_BASEURI;
        RestAssured.basePath = url;

        return given().
                contentType("application/json").
                headers("Content-Type", ContentType.JSON).
                body(modelApi).
                when().
                post().
                then().
                statusCode(200).
                extract().
                response();
    }
}
