package org.max.lesson3.home.accuweather;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.max.lesson3.seminar.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.seminar.accuweather.weather.DailyForecast;
import org.max.lesson3.seminar.accuweather.weather.Weather;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

public class GetWeatherTest extends AccuweatherAbstractTest {

    @ParameterizedTest
    @CsvSource({"1, one_day_forecast_url"
            , "10, ten_day_forecast_url"
            , "15, fifteen_day_forecast_url"})
    void getWeatherTest(int daysAmount, String endPoint) {
        Weather response = given()
                .queryParam(API_KEY_PARAM, getApiKey())
                .pathParam(LOCATION_KEY_PARAM, 50)
                .when()
                .get(getBaseUrl() + getProperty(endPoint))
                .then()
                .statusCode(200)
                .time(lessThan(2000l))
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
