package tests.api;

import io.restassured.response.Response;
import java.util.concurrent.TimeUnit;
import net.datafaker.Faker;
import objects.api.BookingDates;
import objects.api.BookingDetails;
import org.testng.annotations.Test;
import utilities.helpers.APIHelpers;
import utilities.setups.BaseAPISetup;
import utilities.validations.BookingValidations;

public class CreateBookingTest extends BaseAPISetup {
        Faker faker = new Faker();
        BookingValidations bookingValidations = new BookingValidations();

        @Test
        public void createBookingHappyPath() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingSuccess(bookingRequestBody, response);
        }

        @Test
        public void createBookingHappyPathNullAdditionalNeeds() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(null)
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingSuccess(bookingRequestBody, response);
        }

        @Test
        public void createBookingNullFirstName() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(null)
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }

        @Test
        public void createBookingNullLastName() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(null)
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }

        @Test
        public void createBookingNullTotalPrice() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(null)
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }

        @Test
        public void createBookingNullDepositPaid() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(null)
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }

        @Test
        public void createBookingNullBookingDates() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(null)
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }

        @Test
        public void createBookingNullCheckin() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(null)
                                                .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                                                "YYYY-MM-dd"))
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }

        @Test
        public void createBookingNullCheckout() {
                BookingDetails bookingRequestBody = BookingDetails.builder()
                                .firstname(faker.name().firstName())
                                .lastname(faker.name().lastName())
                                .totalprice(faker.number().numberBetween(50, 1000))
                                .depositpaid(faker.bool().bool())
                                .bookingdates(BookingDates.builder()
                                                .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS, "YYYY-MM-dd"))
                                                .checkout(null)
                                                .build())
                                .additionalneeds(faker.boardgame().name())
                                .build();
                Response response = APIHelpers.postRequestWithReportingForCreateBooking(bookingRequestBody);

                bookingValidations.validateBookingInternalServerErrorFailure(response);
        }
}