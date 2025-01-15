package com.sample.restfulbooker.utilities.helpers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.objects.api.BookingDates;
import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.objects.api.Token;
import com.sample.restfulbooker.utilities.Loggers;
import com.sample.restfulbooker.utilities.PropertiesManager;
import com.sample.restfulbooker.utilities.RestFilter;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.SkipException;

import static io.restassured.RestAssured.given;

/* Helpers - API Hitter
 * This class is for creating the request of the API to avoid putting everything in the test classes.
 * I suggest to separate API helpers per each endpoint unless only few will be implemented just like this example. */
public class APIHelpers {
    private static final PropertiesManager pm = new PropertiesManager();

    public static void checkServiceHealth() {
        String skipDueToOutageMessage = "API Service is down. Skipping the test.";
        String skipDueToCodeIssueMessage = "API Service Health was not checked due to code issue. Exception Message: ";

        try {
            int statusCode;

            statusCode = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .get(pm.getAPIProperties().getProperty("ENDPOINT_HEALTHCHECK"))
                    .statusCode();

            if (statusCode != 201) {
                Loggers.warn(skipDueToOutageMessage);
                throw new SkipException(skipDueToOutageMessage);
            }
        } catch (IOException e) {
            Loggers.error(skipDueToCodeIssueMessage + e.getMessage());
        }
    }

    public static Booking createBookingWithRandomData() {
        Booking booking = null;

        try {
            Faker faker = new Faker();
            BookingDetails bookingRequestBody = BookingDetails.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .totalprice(faker.number().numberBetween(50, 1000))
                    .depositpaid(faker.bool().bool())
                    .bookingdates(BookingDates.builder()
                            .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS,
                                    "YYYY-MM-dd"))
                            .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                    "YYYY-MM-dd"))
                            .build())
                    .additionalneeds(faker.boardgame().name())
                    .build();
            Response response = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(bookingRequestBody)
                    .post(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING"));
            booking = response.getBody().as(Booking.class);
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return booking;
    }

    public static Response deleteRequestWithReportingForDeleteBooking(String token, Integer bookingId) {
        Response response = null;

        try {
            response = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .header("Cookie", "token=" + token)
                    .filter(new RestFilter())
                    .delete(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING") + "/"
                            + bookingId);
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return response;
    }

    public static Response getRequestWithReportingForGetBooking(Integer bookingId) {
        Response response = null;

        try {
            response = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .filter(new RestFilter())
                    .get(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING") + "/" + bookingId);
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return response;
    }

    public static Response postRequestWithReportingForCreateBooking(BookingDetails bookingRequestBody) {
        Response response = null;

        try {
            response = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(bookingRequestBody)
                    .filter(new RestFilter())
                    .post(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING"));
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return response;
    }

    public static Response putRequestWithReportingForUpdateBooking(String token, Integer bookingId, BookingDetails bookingRequestBody) {
        Response response = null;

        try {
            response = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Cookie", "token=" + token)
                    .body(bookingRequestBody)
                    .filter(new RestFilter())
                    .put(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING") + "/" + bookingId);
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return response;
    }

    public static String generateToken() {
        String token = "";

        try {
            Token tokenRequestBody = Token.builder()
                    .username(pm.getAPIProperties().getProperty("AUTH_USERNAME"))
                    .password(pm.getAPIProperties().getProperty("AUTH_PASSWORD"))
                    .build();

            token = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .header("Content-Type", "application/json")
                    .body(tokenRequestBody)
                    .post(pm.getAPIProperties().getProperty("ENDPOINT_AUTH_CREATETOKEN"))
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("token")
                    .toString();
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return token;
    }
}