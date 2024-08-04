package com.practicesoftwaretesting.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController<T> {

    private static final String BASE_URI = "https://api.practicesoftwaretesting.com";

    private String authToken;

    protected RequestSpecification baseClient() {
        var requestSpecification = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
        if (authToken != null) {
            requestSpecification.header("Authorization", "Bearer" + authToken);
        }
        return requestSpecification;
    }

    public T withToken(String token) {
        this.authToken = token;
        return (T) this;
    }

    public void cleanToken() {
        this.authToken = null;
    }
}