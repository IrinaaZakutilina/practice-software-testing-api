package com.practicesoftwaretesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class UserApiTest {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.practicesoftwaretesting.com")
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void testBrands() {
        RestAssured.given()
                .get("/brands")
                .then()
                .statusCode(200);
    }

    @Test
    void testUser() {
        // Register user
        var registerUserRequest = """
                {
                  "first_name": "John",
                  "last_name": "Lennon",
                  "address": "Street 1",
                  "city": "City",
                  "state": "State",
                  "country": "Country",
                  "postcode": "1234AA",
                  "phone": "0987654321",
                  "dob": "1941-01-01",
                  "password": "12Example#",
                  "email": "john@lennon.example"
                }
                """;
        var registerUserResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registerUserRequest)
                .post("/users/register")
                .then()
                .statusCode(201)
                .extract()
                .as(RegisterUserResponse.class);

        // Login user
        var loginRequestBody = """
                {
                  "email": "john@lennon.example",
                  "password": "12Example#"
                }
                """;

        var userLoginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequestBody)
                .post("/users/login")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        // Verify user details
        var userId = registerUserResponse.getId();
        RestAssured.given()
                .header("Authorization", "Bearer " + userLoginResponse.getAccessToken())
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("email", equalTo("john@lennon.example"))
                .body("first_name", equalTo("John"))
                .body("last_name", equalTo("Lennon"));

        // Update user information
        var updateUserRequest = """
                {
                  "first_name": "Johnny",
                  "last_name": "Lennon",
                  "address": "Street 2",
                  "city": "New City",
                  "state": "New State",
                  "country": "New Country",
                  "postcode": "5678BB",
                  "phone": "1234567890",
                  "dob": "1942-01-01",
                  "email": "johnny@lennon.example"
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userLoginResponse.getAccessToken())
                .body(updateUserRequest)
                .put("/users/" + userId)
                .then()
                .statusCode(200);

        // Verify updated user details
        RestAssured.given()
                .header("Authorization", "Bearer " + userLoginResponse.getAccessToken())
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("email", equalTo("johnny@lennon.example"))
                .body("first_name", equalTo("Johnny"))
                .body("last_name", equalTo("Lennon"));

        // Login as admin
        var adminLoginRequestBody = """
                {
                  "email": "admin@practicesoftwaretesting.com",
                  "password": "welcome01"
                }
                """;
        var adminLoginResponse = RestAssured.given()
                .contentType(JSON)
                .body(adminLoginRequestBody)
                .post("/users/login")
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        // Delete user
        var adminToken = adminLoginResponse.getAccessToken();
        RestAssured.given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + adminToken)
                .delete("/users/" + userId)
                .then()
                .statusCode(204);

        // Verify user deletion
        RestAssured.given()
                .header("Authorization", "Bearer " + adminToken)
                .get("/users/" + userId)
                .then()
                .statusCode(404);
    }
}

