package org.max.home.accuweather;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.max.seminar.accuweather.weather.DailyForecast;
import org.max.seminar.accuweather.weather.Weather;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

public class GetWeatherTest extends AccuweatherAbstractTest {

    @ParameterizedTest
    @CsvSource({"1, one_day_forecast_url, 200"})
    void getWeatherTest(int daysAmount, String endPoint, int code) {
        Weather response = given()
                .queryParam(API_KEY_PARAM, getApiKey())
                .pathParam(LOCATION_KEY_PARAM, 50)
                .when()
                .get(getBaseUrl() + getProperty(endPoint))
                .then()
                .statusCode(200)
                .time(lessThan(10000l))
                .extract()
                .response()
                .body().as(Weather.class);

        assertAll(() -> assertEquals(daysAmount, response.getDailyForecasts().size())
                , () -> response.getDailyForecasts().forEach(this::checkDailyForecast));
    }

    private void checkDailyForecast(DailyForecast dailyForecast) {
        assertNotNull(dailyForecast.getTemperature());
        assertNotNull(dailyForecast.getDate());
        assertNotNull(dailyForecast.getNight());
        assertNotNull(dailyForecast.getEpochDate());
    }
}
