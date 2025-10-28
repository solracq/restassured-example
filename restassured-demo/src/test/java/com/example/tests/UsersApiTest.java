///
/// Tests focuses only on assertions
/// Run the test via command by "mvn test"
/// Or for an specific environment  "mvn test -Denv=stage"
///
package com.example.tests;

import com.example.api.UsersApi;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsersApiTest extends BaseTest {

    @Test
    public void getUser_shouldReturnValidData() {
        // API call is centralized in UserAPI
        Response response = UsersApi.getUserById(2);

        response
                .then()
                .spec(responseSpec)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"));

//        given()
//                .when()
//                .get("/users/2")
//                .then()
//                .statusCode(200)
//                .body("data.id", equalTo(2))
//                .body("data.email", containsString("@reqres.in"));
    }

    @Test
    public void createUser_shouldReturn201AndUserData() {
        Response response = UsersApi.createUser("morpheus", "leader");

        response
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("id", notNullValue());
    }
}
