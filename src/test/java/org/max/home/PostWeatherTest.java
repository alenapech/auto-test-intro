package org.max.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.max.home.accu.weather.Maximum;
import org.max.home.accu.weather.Minimum;
import org.max.home.accu.weather.Temperature;
import org.max.seminar.spoon.ClassifyCuisineDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostWeatherTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(PostWeatherTest.class);

    private static final String END_POINT = "/temperature";
    private static final String CONTENT_TYPE = "application/json";

    private static Stream<Arguments> getPostWeatherTestArgs() {
        return Stream.of(
                Arguments.of(200, "Range", getOkResponseObject()),
                Arguments.of(403, "Precise", "ERROR")
        );
    }

    private static Temperature getOkResponseObject() {
        Temperature bodyResponse = new Temperature();
        Minimum minimum = new Minimum();
        minimum.setValue(0.0);
        Maximum maximum = new Maximum();
        maximum.setValue(10.0);
        bodyResponse.setMinimum(minimum);
        bodyResponse.setMaximum(maximum);
        return bodyResponse;
    }

    @ParameterizedTest
    @MethodSource("getPostWeatherTestArgs")
    public void postWeatherTest(int code, String type, Object responseObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        stubFor(post(urlEqualTo(END_POINT))
                .withHeader("Content-Type", equalTo(CONTENT_TYPE))
                .withRequestBody(containing("\"type\": \"" + type + "\""))
                .willReturn(aResponse()
                        .withStatus(code)
                        .withBody(mapper.writeValueAsString(responseObject))));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(getBaseUrl()+END_POINT);
        request.addHeader("Content-Type", CONTENT_TYPE);
        request.setEntity(new StringEntity("{" +
                "\"type\": \"" + type + "\"" +
                "}"));

        HttpResponse response = httpClient.execute(request);

        verify(postRequestedFor(urlEqualTo(END_POINT))
                .withHeader("Content-Type", equalTo(CONTENT_TYPE)));
        assertEquals(code, response.getStatusLine().getStatusCode());
        logger.debug("response: {}", mapper.readValue(response.getEntity().getContent(), Object.class));
    }

}
