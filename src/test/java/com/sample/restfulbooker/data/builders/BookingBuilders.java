package com.sample.restfulbooker.data.builders;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.sample.restfulbooker.objects.api.BookingDates;
import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.objects.api.Token;
import com.sample.restfulbooker.utilities.Loggers;
import com.sample.restfulbooker.utilities.PropertiesManager;
import net.datafaker.Faker;

public class BookingBuilders {
    private static final PropertiesManager pm = new PropertiesManager();
    Faker faker = new Faker();

    // ================================================== Builders - Start ==================================================
    public BookingDetails createBookingWithFullData() {
        return BookingDetails.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(50, 1000))
                .depositpaid(faker.bool().bool())
                .bookingdates(BookingDates.builder()
                        .checkin(faker.timeAndDate().future(30, TimeUnit.DAYS,
                                "YYYY-MM-dd"))
                        .checkout(faker.timeAndDate().future(60, 30, TimeUnit.DAYS,
                                "YYYY-MM-dd"))
                        .build())
                .additionalneeds(faker.boardgame().name())
                .build();
    }

    public BookingDetails createBookingNullAdditionalNeeds() {
        return BookingDetails.builder()
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
    }

    public BookingDetails createBookingNullFirstName() {
        return BookingDetails.builder()
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
    }

    public BookingDetails createBookingNullLastName() {
        return BookingDetails.builder()
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
    }

    public BookingDetails createBookingNullTotalPrice() {
        return BookingDetails.builder()
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
    }

    public BookingDetails createBookingNullDepositPaid() {
        return BookingDetails.builder()
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
    }

    public BookingDetails createBookingNullBookingDates() {
        return BookingDetails.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(50, 1000))
                .depositpaid(faker.bool().bool())
                .bookingdates(null)
                .additionalneeds(faker.boardgame().name())
                .build();
    }

    public BookingDetails createBookingNullCheckin() {
        return BookingDetails.builder()
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
    }

    public BookingDetails createBookingNullCheckout() {
        return BookingDetails.builder()
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
    }

    public Token getTokenAuth() {
        String username = null;
        String password = null;

        try {
            username = pm.getAPIProperties().getProperty("AUTH_USERNAME");
            password = pm.getAPIProperties().getProperty("AUTH_PASSWORD");
        } catch (IOException e) {
            Loggers.error(e.getMessage());
        }

        return Token.builder()
                .username(username)
                .password(password)
                .build();
    }
    // ================================================== Builders - End ====================================================
}