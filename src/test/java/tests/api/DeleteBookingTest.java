package tests.api;

import io.restassured.response.Response;
import objects.api.Booking;
import org.testng.annotations.Test;
import utilities.helpers.APIHelpers;
import utilities.setups.BaseAPISetup;
import utilities.validations.BookingValidations;

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