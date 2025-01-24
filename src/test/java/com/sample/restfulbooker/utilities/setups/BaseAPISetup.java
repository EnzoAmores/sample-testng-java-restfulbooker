package com.sample.restfulbooker.utilities.setups;

import com.sample.restfulbooker.endpointcalls.BookingEndpointCalls;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeTest;

import static org.hamcrest.Matchers.lessThan;

/* Base Setup - API Only */
public class BaseAPISetup {
    BookingEndpointCalls bookingEndpointCalls = new BookingEndpointCalls();

    @BeforeTest
    public void setup() {
        setupAPIConfig();
        bookingEndpointCalls.checkServiceHealth();
    }

    public void setupAPIConfig() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(10000L)) // Response expected less than 10 seconds.
                .build();
    }
}