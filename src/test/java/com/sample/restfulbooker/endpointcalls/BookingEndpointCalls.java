package com.sample.restfulbooker.endpointcalls;

import java.io.IOException;

import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.objects.api.Token;
import com.sample.restfulbooker.utilities.Loggers;
import com.sample.restfulbooker.utilities.PropertiesManager;
import com.sample.restfulbooker.utilities.RestFilter;
import io.restassured.response.Response;
import org.testng.SkipException;

import static io.restassured.RestAssured.given;

/* Helpers - API Hitter
 * This class is for creating the request of the API to avoid putting everything in the test classes.
 * I suggest to separate API helpers per each endpoint unless only few will be implemented just like this example. */
public class BookingEndpointCalls {
    private static final PropertiesManager pm = new PropertiesManager();

    // ================================================== Endpoint Calls - Start ==================================================
    public void checkServiceHealth() {
        String skipDueToOutageMessage = "API Service is down. Skipping the test.";

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
            Loggers.error(e.getMessage());
        }
    }

    public String postRequestAuth(Token tokenAuthRequestBody) {
        String token = "";

        try {
            token = given()
                    .baseUri(pm.getAPIProperties().getProperty("BASE_URL"))
                    .header("Content-Type", "application/json")
                    .body(tokenAuthRequestBody)
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

    public Response deleteRequestWithReportingForDeleteBooking(String token, Integer bookingId) {
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

    public Response getRequestWithReportingForGetBooking(Integer bookingId) {
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

    public Response postRequestWithReportingForCreateBooking(BookingDetails bookingRequestBody) {
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

    public Response putRequestWithReportingForUpdateBooking(String token, Integer bookingId, BookingDetails bookingRequestBody) {
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
    // ================================================== Endpoint Calls - End ====================================================
}