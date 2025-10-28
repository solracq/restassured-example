package com.example.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersApi {

    public static Response getUserById(int id) {
        return given()
                .when()
                .get("/users/" + id);
    }

    public static Response createUser(String name, String job) {
        String body = String.format("{ \"name\": \"%s\", \"job\": \"%s\" }", name, job);

        return given()
                .body(body)
                .when()
                .post("/users");
    }
}
