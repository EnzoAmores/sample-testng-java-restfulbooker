package com.sample.restfulbooker.utilities.setups;

import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeTest;

import static org.hamcrest.Matchers.lessThan;

/* Base Setup - API Only */
public class BaseAPISetup {
    APIHelpers apiHelpers = new APIHelpers();

    @BeforeTest
    public void setup() {
        setupAPIConfig();
        apiHelpers.checkServiceHealth();
    }

    public void setupAPIConfig() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(10000L)) // Response expected less than 10 seconds.
                .build();
    }
}