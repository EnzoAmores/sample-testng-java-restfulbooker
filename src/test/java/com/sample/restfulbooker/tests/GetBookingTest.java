package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.data.BookingData;
import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.endpointcalls.BookingEndpointCalls;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetBookingTest extends BaseAPISetup {
    BookingData bookingData = new BookingData();
    BookingEndpointCalls bookingEndpointCalls = new BookingEndpointCalls();
    BookingValidations bookingValidations = new BookingValidations();

    // ================================================== Test Methods - Start ==================================================
    @Test
    public void getBookingHappyPath() {
        Booking booking = bookingData.createNewBooking();
        Response response = bookingEndpointCalls.getRequestWithReportingForGetBooking(booking.getBookingid());

        bookingValidations.validateBookingDetailsSuccess(booking.getBooking(), response);
    }

    @Test
    public void getBookingBadId() {
        Response response = bookingEndpointCalls.getRequestWithReportingForGetBooking(null);

        bookingValidations.validateBookingNotFoundFailure(response);
    }
    // ================================================== Test Methods - End ====================================================
}