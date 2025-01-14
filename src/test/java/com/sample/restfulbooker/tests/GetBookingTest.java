package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.objects.api.Booking;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.utilities.validations.BookingValidations;

public class GetBookingTest extends BaseAPISetup {
    BookingValidations bookingValidations = new BookingValidations();

    @Test
    public void getBookingHappyPath() {
        APIHelpers.checkServiceHealth();

        Booking booking = APIHelpers.createBookingWithRandomData();
        Response response = APIHelpers.getRequestWithReportingForGetBooking(booking.getBookingid());

        bookingValidations.validateBookingDetailsSuccess(booking.getBooking(), response);
    }

    @Test
    public void getBookingBadId() {
        APIHelpers.checkServiceHealth();

        Response response = APIHelpers.getRequestWithReportingForGetBooking(null);

        bookingValidations.validateBookingNotFoundFailure(response);
    }
}