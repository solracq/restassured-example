///
/// Simple and standalone test
///

package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*; // Allows you to use the needed method w/o using the Class name e.g. Math.trunc() -> trunc()
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
    @Test
    public void testGetRequest(){
        baseURI = "https://www.alphavantage.co/query";
        String apiKey = "NEW_API_KEY"; // Get it at https://www.alphavantage.co/support/#api-key
        Response response = given()        // Set up the request details (e.g. headers, parameters)
                                .header("Authorization", "Bearer " + apiKey)
                                .queryParam("function", "CURRENCY_EXCHANGE_RATE")
                                .queryParam("from_currency", "CAD")
                                .queryParam("to_currency", "MXN")
                                .queryParam("apikey", apiKey)
                            .when()        // Specify the action (e.g. the HTTP method and endpoint)
                                .get(baseURI)
                            .then()        // Assert the expected outcomes (e.g. status code, response body)
                                .statusCode(200)
                            .extract().response();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
    }
}

