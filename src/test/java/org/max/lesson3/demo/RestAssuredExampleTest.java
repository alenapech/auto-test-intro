package org.max.lesson3.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/*
    Простой демо тест
 */
public class RestAssuredExampleTest {

    @Test
    @Disabled
    void test() {
        given()
        .when().get("https://www.google.com/")
        .then().statusCode(200);
    }
}
