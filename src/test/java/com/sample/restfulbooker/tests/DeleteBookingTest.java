package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.utilities.validations.BookingValidations;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBookingTest extends BaseAPISetup {
    BookingValidations bookingValidations = new BookingValidations();

    @Test
    public void deleteBookingHappyPath() {
        APIHelpers.checkServiceHealth();

        Booking booking = APIHelpers.createBookingWithRandomData();
        Response response = APIHelpers.deleteRequestWithReportingForDeleteBooking(APIHelpers.generateToken(),
                booking.getBookingid());

        bookingValidations.validateDeleteBookingSuccess(response);
    }

    @Test
    public void deleteBookingBadToken() {
        APIHelpers.checkServiceHealth();

        Booking booking = APIHelpers.createBookingWithRandomData();
        Response response = APIHelpers.deleteRequestWithReportingForDeleteBooking(null, booking.getBookingid());

        bookingValidations.validateBookingForbiddenFailure(response);
    }

    @Test
    public void deleteBookingBadId() {
        APIHelpers.checkServiceHealth();

        Response response = APIHelpers.deleteRequestWithReportingForDeleteBooking(APIHelpers.generateToken(),
                null);

        bookingValidations.validateBookingMethodNotAllowedFailure(response);
    }
}