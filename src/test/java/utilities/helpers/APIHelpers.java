package utilities.helpers;

import io.restassured.response.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import net.datafaker.Faker;
import objects.api.Booking;
import objects.api.BookingDates;
import objects.api.BookingDetails;
import objects.api.Token;
import utilities.Log;
import utilities.PropertiesManager;
import utilities.RestFilter;
import static io.restassured.RestAssured.given;

public class APIHelpers {
        private static PropertiesManager pm = new PropertiesManager();

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
                                        .header("Accept", "application/json")
                                        .header("Content-Type", "application/json")
                                        .body(bookingRequestBody)
                                        .post(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING"));
                        booking = response.getBody().as(Booking.class);
                } catch (IOException e) {
                        Log.error(e.getMessage());
                }

                return booking;
        }

        public static Response deleteRequestWithReportingForDeleteBooking(String token, Integer bookingId) {
                Response response = null;

                try {
                        response = given()
                                        .header("Cookie", "token=" + token)
                                        .filter(new RestFilter())
                                        .delete(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING") + "/"
                                                        + bookingId);
                } catch (IOException e) {
                        Log.error(e.getMessage());
                }

                return response;
        }

        public static Response getRequestWithReportingForGetBooking(Integer bookingId) {
                Response response = null;

                try {
                        response = given()
                                        .filter(new RestFilter())
                                        .get(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING") + "/" + bookingId);
                } catch (IOException e) {
                        Log.error(e.getMessage());
                }

                return response;
        }

        public static Response postRequestWithReportingForCreateBooking(BookingDetails bookingRequestBody) {
                Response response = null;

                try {
                        response = given()
                                        .header("Accept", "application/json")
                                        .header("Content-Type", "application/json")
                                        .body(bookingRequestBody)
                                        .filter(new RestFilter())
                                        .post(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING"));
                } catch (IOException e) {
                        Log.error(e.getMessage());
                }

                return response;
        }

        public static Response putRequestWithReportingForUpdateBooking(String token, Integer bookingId,
                        BookingDetails bookingRequestBody) {
                Response response = null;

                try {
                        response = given()
                                        .header("Accept", "application/json")
                                        .header("Content-Type", "application/json")
                                        .header("Cookie", "token=" + token)
                                        .body(bookingRequestBody)
                                        .filter(new RestFilter())
                                        .put(pm.getAPIProperties().getProperty("ENDPOINT_BOOKING") + "/" + bookingId);
                } catch (IOException e) {
                        Log.error(e.getMessage());
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
                                        .header("Content-Type", "application/json")
                                        .body(tokenRequestBody)
                                        .post(pm.getAPIProperties().getProperty("ENDPOINT_AUTH_CREATETOKEN"))
                                        .then()
                                        .statusCode(200)
                                        .extract()
                                        .path("token")
                                        .toString();
                } catch (IOException e) {
                        Log.error(e.getMessage());
                }

                return token;
        }
}