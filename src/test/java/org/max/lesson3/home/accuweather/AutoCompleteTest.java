package org.max.lesson3.home.accuweather;

import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.seminar.accuweather.location.Location;
import org.max.lesson3.seminar.accuweather.weather.DailyForecast;
import org.max.lesson3.seminar.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AutoCompleteTest extends AccuweatherAbstractTest {

    @Test
    void autoCompleteTest() {
        List<Location> response = given()
                .queryParam(API_KEY_PARAM, getApiKey())
                .queryParam(AUTOCOMPLETE_PARAM, "Almaty")
                .when()
                .get(getBaseUrl() + getProperty("autocomplete_url"))
                .then()
                .statusCode(200)
                .time(lessThan(2000l))
                .extract()
                .body()
                .jsonPath()
                .getList(".", Location.class);

        assertAll(() -> assertEquals(1, response.size())
                , () -> response.forEach(this::checkLocation)
                , () -> assertEquals("KZ", response.get(0).getCountry().getId())
                , () -> assertEquals("ALA", response.get(0).getAdministrativeArea().getId())
                , () -> assertEquals("Almaty", response.get(0).getLocalizedName())
                , () -> assertEquals("City", response.get(0).getType()));
    }

    private void checkLocation(Location location) {
        assertNotNull(location.getType());
        assertNotNull(location.getLocalizedName());
        assertNotNull(location.getCountry());
        assertNotNull(location.getCountry().getId());
        assertNotNull(location.getAdministrativeArea());
        assertNotNull(location.getAdministrativeArea().getId());
    }

}
