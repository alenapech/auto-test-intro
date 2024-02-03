package org.max.lesson3.home.spoonacular;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClassifyCuisineTestSpoonacular extends SpoonacularAbstractTest {

    @Test
    @Disabled
    void postClassifyCuisine_whenValid_shouldReturn() {
        ClassifyCuisineDTO response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .formParam("title","Pork roast with green beans")
                .formParam("ingredientList","3 oz pork shoulder")
                .formParam("language","en")
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(1000l))
                .extract()
                .response()
                .body()
                .as(ClassifyCuisineDTO.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        Assertions.assertEquals(response.getConfidence(),0.0);
        Assertions.assertEquals(response.getCuisines().size(),3);
    }
}
