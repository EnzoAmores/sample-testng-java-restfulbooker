package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.data.BookingData;
import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.endpointcalls.BookingEndpointCalls;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseAPISetup {
    BookingData bookingData = new BookingData();
    BookingEndpointCalls bookingEndpointCalls = new BookingEndpointCalls();
    BookingValidations bookingValidations = new BookingValidations();

    // ================================================== Test Methods - Start ==================================================
    @Test
    public void deleteBookingHappyPath() {
        Booking booking = bookingData.createNewBooking();
        Response response = bookingEndpointCalls.deleteRequestWithReportingForDeleteBooking(bookingData.generateToken(), booking.getBookingid());

        bookingValidations.validateDeleteBookingSuccess(response);
    }

    @Test
    public void deleteBookingBadToken() {
        Booking booking = bookingData.createNewBooking();
        Response response = bookingEndpointCalls.deleteRequestWithReportingForDeleteBooking(null, booking.getBookingid());

        bookingValidations.validateBookingForbiddenFailure(response);
    }

    @Test
    public void deleteBookingBadId() {
        Response response = bookingEndpointCalls.deleteRequestWithReportingForDeleteBooking(bookingData.generateToken(), null);

        bookingValidations.validateBookingMethodNotAllowedFailure(response);
    }
    // ================================================== Test Methods - End ====================================================
}