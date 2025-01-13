package utilities.setups;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;

import utilities.Loggers;
import utilities.PropertiesManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

/* Base Setup - API Only */
public class BaseAPISetup {
    private static PropertiesManager pm = new PropertiesManager();

    @BeforeTest
    public void setup() {
        setupAPIConfig();
        checkAPIHealth();
    }

    public void setupAPIConfig() {
        try {
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .build();
            RestAssured.responseSpecification = new ResponseSpecBuilder()
                    .expectResponseTime(lessThan(30000L)) // Response expected less than 30 seconds.
                    .build();
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }
    }

    /* Created for checking API health first. If it is down, will skip the test. */
    public void checkAPIHealth() {
        String skipMessage = "API Service is down. Skipping the test.";

        try {
            Integer statusCode;

            statusCode = given()
                    .get(pm.getAPIProperties().getProperty("ENDPOINT_HEALTHCHECK"))
                    .statusCode();

            if (statusCode != 201) {
                Loggers.warn(skipMessage);
                throw new SkipException(skipMessage);
            }
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }
    }
}