package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.utilities.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetBookingTest extends BaseAPISetup {
    APIHelpers apiHelpers = new APIHelpers();
    BookingValidations bookingValidations = new BookingValidations();

    // ================================================== Test Methods - Start ==================================================
    @Test
    public void getBookingHappyPath() {
        Booking booking = apiHelpers.createBookingWithRandomData();
        Response response = apiHelpers.getRequestWithReportingForGetBooking(booking.getBookingid());

        bookingValidations.validateBookingDetailsSuccess(booking.getBooking(), response);
    }

    @Test
    public void getBookingBadId() {
        Response response = apiHelpers.getRequestWithReportingForGetBooking(null);

        bookingValidations.validateBookingNotFoundFailure(response);
    }
    // ================================================== Test Methods - End ====================================================
}