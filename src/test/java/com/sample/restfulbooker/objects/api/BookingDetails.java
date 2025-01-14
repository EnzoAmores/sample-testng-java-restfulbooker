package com.sample.restfulbooker.objects.api;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import lombok.Getter;

@Builder
@Getter
@Jacksonized
public class BookingDetails {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}