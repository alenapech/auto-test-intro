package org.max.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.max.home.accu.weather.DailyForecast;
import org.max.home.accu.weather.Night;
import org.max.home.accu.weather.Temperature;
import org.max.home.accu.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

public class GetWeatherTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(GetWeatherTest.class);

    @ParameterizedTest
    @CsvSource({"1, /forecasts/v1/daily/1day"
            , "10, /forecasts/v1/daily/10day"})
    void getWeatherTest(int daysAmount, String endPoint) throws JsonProcessingException {
        logger.debug("getWeatherTest");
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = new Weather();
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        for (int i = 0; i < daysAmount; i++) {
            dailyForecasts.add(getMockedDailyForecast());
        }
        weather.setDailyForecasts(dailyForecasts);
        logger.debug("Mock for GET {}", endPoint);
        stubFor(get(urlPathEqualTo(endPoint))
                .willReturn(aResponse()
                        .withStatus(200).withBody(mapper.writeValueAsString(weather))));

        Weather response = given()
                .when()
                .get(getBaseUrl() + endPoint)
                .then()
                .statusCode(200)
                .time(lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        assertAll(() -> assertEquals(daysAmount, response.getDailyForecasts().size())
                , () -> response.getDailyForecasts().forEach(this::checkDailyForecast));
    }

    private DailyForecast getMockedDailyForecast() {
        DailyForecast dailyForecast = new DailyForecast();
        dailyForecast.setTemperature(new Temperature());
        dailyForecast.setDate("");
        dailyForecast.setNight(new Night());
        dailyForecast.setEpochDate((int) (new Date()).getTime());
        return dailyForecast;
    }

    private void checkDailyForecast(DailyForecast dailyForecast) {
        assertNotNull(dailyForecast.getTemperature());
        assertNotNull(dailyForecast.getDate());
        assertNotNull(dailyForecast.getNight());
        assertNotNull(dailyForecast.getEpochDate());
    }
}
