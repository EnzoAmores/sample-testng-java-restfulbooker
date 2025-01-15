package com.sample.restfulbooker.utilities.validations;

import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.objects.api.BookingDetails;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/* Validations - Booking */
public class BookingValidations {
    private final SoftAssert assertions = new SoftAssert();

    public void validateBookingBadRequestFailure(Response response) {
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.getBody().asPrettyString(), "Bad Request");
    }

    public void validateBookingForbiddenFailure(Response response) {
        Assert.assertEquals(response.statusCode(), 403);
        Assert.assertEquals(response.getBody().asPrettyString(), "Forbidden");
    }

    public void validateBookingInternalServerErrorFailure(Response response) {
        Assert.assertEquals(response.statusCode(), 500);
        Assert.assertEquals(response.getBody().asPrettyString(), "Internal Server Error");
    }

    public void validateBookingMethodNotAllowedFailure(Response response) {
        Assert.assertEquals(response.statusCode(), 405);
        Assert.assertEquals(response.getBody().asPrettyString(), "Method Not Allowed");
    }

    public void validateBookingNotFoundFailure(Response response) {
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.getBody().asPrettyString(), "Not Found");
    }

    public void validateBookingDetailsSuccess(BookingDetails bookingRequestBody, Response response) {
        final BookingDetails bookingResponseBody = response.getBody().as(BookingDetails.class);

        Assert.assertEquals(response.statusCode(), 200);

        assertions.assertEquals(bookingResponseBody.getFirstname(), bookingRequestBody.getFirstname());
        assertions.assertEquals(bookingResponseBody.getLastname(), bookingRequestBody.getLastname());
        assertions.assertEquals(bookingResponseBody.getTotalprice(), bookingRequestBody.getTotalprice());
        assertions.assertEquals(bookingResponseBody.getDepositpaid(), bookingRequestBody.getDepositpaid());
        assertions.assertEquals(bookingResponseBody.getAdditionalneeds(), bookingRequestBody.getAdditionalneeds());
        assertions.assertEquals(bookingResponseBody.getBookingdates().getCheckin(), bookingRequestBody.getBookingdates().getCheckin());
        assertions.assertEquals(bookingResponseBody.getBookingdates().getCheckout(), bookingRequestBody.getBookingdates().getCheckout());
        assertions.assertAll();
    }

    public void validateBookingSuccess(BookingDetails bookingRequestBody, Response response) {
        final Booking bookingResponseBody = response.getBody().as(Booking.class);

        Assert.assertEquals(response.statusCode(), 200);

        assertions.assertNotNull(bookingResponseBody.getBookingid());
        assertions.assertEquals(bookingResponseBody.getBooking().getFirstname(), bookingRequestBody.getFirstname());
        assertions.assertEquals(bookingResponseBody.getBooking().getLastname(), bookingRequestBody.getLastname());
        assertions.assertEquals(bookingResponseBody.getBooking().getTotalprice(), bookingRequestBody.getTotalprice());
        assertions.assertEquals(bookingResponseBody.getBooking().getDepositpaid(), bookingRequestBody.getDepositpaid());
        assertions.assertEquals(bookingResponseBody.getBooking().getAdditionalneeds(), bookingRequestBody.getAdditionalneeds());
        assertions.assertEquals(bookingResponseBody.getBooking().getBookingdates().getCheckin(), bookingRequestBody.getBookingdates().getCheckin());
        assertions.assertEquals(bookingResponseBody.getBooking().getBookingdates().getCheckout(), bookingRequestBody.getBookingdates().getCheckout());
        assertions.assertAll();
    }

    public void validateDeleteBookingSuccess(Response response) {
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.getBody().asPrettyString(), "Created");
    }
}