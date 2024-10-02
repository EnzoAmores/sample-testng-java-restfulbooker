package tests.api;

import io.restassured.response.Response;
import objects.api.Booking;
import org.testng.annotations.Test;
import utilities.helpers.APIHelpers;
import utilities.setups.BaseAPISetup;
import utilities.validations.BookingValidations;

public class GetBookingTest extends BaseAPISetup {
        BookingValidations bookingValidations = new BookingValidations();

        @Test
        public void getBookingHappyPath() {
                Booking booking = APIHelpers.createBookingWithRandomData();
                Response response = APIHelpers.getRequestWithReportingForGetBooking(booking.getBookingid());

                bookingValidations.validateBookingDetailsSuccess(booking.getBooking(), response);
        }

        @Test
        public void getBookingBadId() {
                Response response = APIHelpers.getRequestWithReportingForGetBooking(null);

                bookingValidations.validateBookingNotFoundFailure(response);
        }
}