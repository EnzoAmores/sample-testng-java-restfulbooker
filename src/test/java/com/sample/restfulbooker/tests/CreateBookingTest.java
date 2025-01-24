package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.data.builders.BookingBuilders;
import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.endpointcalls.BookingEndpointCalls;
import com.sample.restfulbooker.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateBookingTest {
    BookingBuilders bookingBuilders = new BookingBuilders();
    BookingEndpointCalls bookingEndpointCalls = new BookingEndpointCalls();
    BookingValidations bookingValidations = new BookingValidations();

    // ================================================== Test Methods - Start ==================================================
    @Test
    public void createBookingHappyPath() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingSuccess(bookingRequestBody, response);
    }

    @Test
    public void createBookingHappyPathAgain() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingSuccess(bookingRequestBody, response);
    }

    @Test
    public void createBookingHappyPathAgainAgain() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingSuccess(bookingRequestBody, response);
    }

    @Test
    public void createBookingHappyPathAgainAgainAgain() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingSuccess(bookingRequestBody, response);
    }

    @Test
    public void createBookingHappyPathNullAdditionalNeeds() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullAdditionalNeeds();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingSuccess(bookingRequestBody, response);
    }

    @Test
    public void createBookingNullFirstName() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullFirstName();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }

    @Test
    public void createBookingNullLastName() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullLastName();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }

    @Test
    public void createBookingNullTotalPrice() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullTotalPrice();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }

    @Test
    public void createBookingNullDepositPaid() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullDepositPaid();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }

    @Test
    public void createBookingNullBookingDates() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullBookingDates();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }

    @Test
    public void createBookingNullCheckin() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullCheckin();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }

    @Test
    public void createBookingNullCheckout() {
        BookingDetails bookingRequestBody = bookingBuilders.createBookingNullCheckout();
        Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

        bookingValidations.validateBookingInternalServerErrorFailure(response);
    }
    // ================================================== Test Methods - End ====================================================
}