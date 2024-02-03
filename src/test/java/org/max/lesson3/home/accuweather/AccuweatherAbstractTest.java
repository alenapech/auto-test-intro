package org.max.lesson3.home.accuweather;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AccuweatherAbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;

    protected static final String API_KEY_PARAM = "apikey";
    protected static final String LOCATION_KEY_PARAM = "locationKey";
    protected static final String AUTOCOMPLETE_PARAM = "q";

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/test/resources/accuweather.properties");
        prop.load(configFile);
    }

    @AfterAll
    static void tearDown() throws IOException {
        configFile.close();
    }

    protected static String getApiKey() {
        return getProperty(API_KEY_PARAM);
    }

    protected static String getBaseUrl() {
        return getProperty("base_url");
    }

    protected static String getProperty(String key) {
        return prop.getProperty(key);
    }

}
