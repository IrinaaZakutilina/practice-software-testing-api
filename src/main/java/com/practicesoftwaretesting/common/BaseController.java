package com.practicesoftwaretesting.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController {

    private static final String BASE_URI = "https://api.practicesoftwaretesting.com";

    protected RequestSpecification baseClient() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}