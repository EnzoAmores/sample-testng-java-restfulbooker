package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.data.BookingData;
import com.sample.restfulbooker.data.builders.BookingBuilders;
import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.endpointcalls.BookingEndpointCalls;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UpdateBookingTest extends BaseAPISetup {
    BookingData bookingData = new BookingData();
    BookingBuilders bookingBuilders = new BookingBuilders();
    BookingEndpointCalls bookingEndpointCalls = new BookingEndpointCalls();
    BookingValidations bookingValidations = new BookingValidations();

    // ================================================== Test Methods - Start ==================================================
    @Test
    public void updateBookingHappyPathAllFields() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingDetailsSuccess(updateBookingRequestBody, response);
    }

    @Test
    public void updateBookingHappyPathNullAdditionalNeeds() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullAdditionalNeeds();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingDetailsSuccess(updateBookingRequestBody, response);
    }

    @Test
    public void updateBookingBadRequestNullFirstName() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullFirstName();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullLastName() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullLastName();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullTotalPrice() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullTotalPrice();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullDepositPaid() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullDepositPaid();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullBookingDates() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullBookingDates();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullCheckin() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullCheckin();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullCheckout() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingNullCheckout();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadToken() {
        Booking booking = bookingData.createNewBooking();
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(null, booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingForbiddenFailure(response);
    }

    @Test
    public void updateBookingBadId() {
        BookingDetails updateBookingRequestBody = bookingBuilders.createBookingWithFullData();
        Response response = bookingEndpointCalls.putRequestWithReportingForUpdateBooking(bookingData.generateToken(), null, updateBookingRequestBody);

        bookingValidations.validateBookingMethodNotAllowedFailure(response);
    }
    // ================================================== Test Methods - End ====================================================
}