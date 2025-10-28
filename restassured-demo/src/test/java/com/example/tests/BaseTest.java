///
/// BaseTest contains shared configs
/// Environment can be override by  "mvn test -Denv=stage"
/// In REST Assured, a RequestSpecification defines defaults (like base URI, headers, content type, etc.),
/// and a ResponseSpecification defines expected defaults (like status codes, response time, etc.).
///
package com.example.tests;

import com.example.config.Environment;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeAll
    public static void setup(){
        // Load environment, you can switch this via a system property
        String env = System.getProperty("env", "dev");
        Environment.load(env);

        // Set the base URI for all tests
        RestAssured.baseURI = Environment.get("base.url");

        // Build reusable request spec
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();

        // Build reusable response spec
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();
    }
}
