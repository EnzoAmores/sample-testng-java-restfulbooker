package com.sample.restfulbooker.tests;

import com.sample.restfulbooker.objects.api.BookingDates;
import com.sample.restfulbooker.objects.api.BookingDetails;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.annotations.Test;
import com.sample.restfulbooker.utilities.helpers.APIHelpers;
import com.sample.restfulbooker.utilities.setups.BaseAPISetup;
import com.sample.restfulbooker.utilities.validations.BookingValidations;

import java.util.concurrent.TimeUnit;

public class CreateBookingTest extends BaseAPISetup {
    Faker faker = new Faker();
    BookingValidations bookingValidations = new BookingValidations();

    @Test
    public void createBookingHappyPath() {
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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
        APIHelpers.checkServiceHealth();

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