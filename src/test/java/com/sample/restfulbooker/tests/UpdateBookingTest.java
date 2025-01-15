package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.objects.api.BookingDates;
import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.utilities.validations.BookingValidations;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UpdateBookingTest extends BaseAPISetup {
    Faker faker = new Faker();
    BookingValidations bookingValidations = new BookingValidations();

    @Test
    public void updateBookingHappyPathAllFields() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingDetailsSuccess(updateBookingRequestBody, response);
    }

    @Test
    public void updateBookingHappyPathNullAdditionalNeeds() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingDetailsSuccess(updateBookingRequestBody, response);
    }

    @Test
    public void updateBookingBadRequestNullFirstName() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullLastName() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullTotalPrice() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullDepositPaid() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullBookingDates() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(50, 1000))
                .depositpaid(faker.bool().bool())
                .bookingdates(null)
                .additionalneeds(faker.boardgame().name())
                .build();
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullCheckin() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadRequestNullCheckout() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingBadRequestFailure(response);
    }

    @Test
    public void updateBookingBadToken() {
        Booking booking = APIHelpers.createBookingWithRandomData();
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(null, booking.getBookingid(), updateBookingRequestBody);

        bookingValidations.validateBookingForbiddenFailure(response);
    }

    @Test
    public void updateBookingBadId() {
        BookingDetails updateBookingRequestBody = BookingDetails.builder()
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
        Response response = APIHelpers.putRequestWithReportingForUpdateBooking(APIHelpers.generateToken(), null, updateBookingRequestBody);

        bookingValidations.validateBookingMethodNotAllowedFailure(response);
    }
}