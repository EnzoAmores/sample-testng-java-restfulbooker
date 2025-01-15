package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.utilities.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseAPISetup {
    APIHelpers apiHelpers = new APIHelpers();
    BookingValidations bookingValidations = new BookingValidations();

    // ================================================== Test Methods - Start ==================================================
    @Test
    public void deleteBookingHappyPath() {
        Booking booking = apiHelpers.createBookingWithRandomData();
        Response response = apiHelpers.deleteRequestWithReportingForDeleteBooking(apiHelpers.generateToken(), booking.getBookingid());

        bookingValidations.validateDeleteBookingSuccess(response);
    }

    @Test
    public void deleteBookingBadToken() {
        Booking booking = apiHelpers.createBookingWithRandomData();
        Response response = apiHelpers.deleteRequestWithReportingForDeleteBooking(null, booking.getBookingid());

        bookingValidations.validateBookingForbiddenFailure(response);
    }

    @Test
    public void deleteBookingBadId() {
        Response response = apiHelpers.deleteRequestWithReportingForDeleteBooking(apiHelpers.generateToken(), null);

        bookingValidations.validateBookingMethodNotAllowedFailure(response);
    }
    // ================================================== Test Methods - End ====================================================
}