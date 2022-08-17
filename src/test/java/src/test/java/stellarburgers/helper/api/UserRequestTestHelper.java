package src.test.java.stellarburgers.helper.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserRequestTestHelper {

    private static final String STELLAR_BURGERS_URL = "https://stellarburgers.nomoreparties.site";

    private static final String REGISTER_URL = "/api/auth/register";
    private static final String LOGIN_URL = "/api/auth/login";
    private static final String AUTH_USER_URL = "/api/auth/user";

    public static Response createUserRequest(Map<String, String> body) {
        return given()
                .spec(buildSpecWithoutAuthorization())
                .and()
                .body(body)
                .when()
                .post(REGISTER_URL);
    }

    public static Response loginUserRequest(Map<String, String> body) {
        return given()
                .spec(buildSpecWithoutAuthorization())
                .and()
                .body(body)
                .when()
                .post(LOGIN_URL);
    }

    public static Response deleteUser(String accessToken) {
        return given()
                .spec(buildSpecWithAuthorization(accessToken))
                .when()
                .delete(AUTH_USER_URL);
    }

    private static RequestSpecBuilder createRequestSpecificationBuilder() {
        return new RequestSpecBuilder()
                .addHeader("Content-type", "application/json")
                .setBaseUri(STELLAR_BURGERS_URL);
    }

    private static RequestSpecification buildSpecWithoutAuthorization() {
        return createRequestSpecificationBuilder().build();
    }

    private static RequestSpecification buildSpecWithAuthorization(String accessToken) {
        return createRequestSpecificationBuilder()
                .addHeader("Authorization", accessToken)
                .build();
    }
}

