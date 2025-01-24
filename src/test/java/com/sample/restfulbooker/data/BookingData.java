package com.sample.restfulbooker.data;

import com.sample.restfulbooker.data.builders.BookingBuilders;
import com.sample.restfulbooker.endpointcalls.BookingEndpointCalls;
import com.sample.restfulbooker.objects.api.Booking;
import com.sample.restfulbooker.objects.api.BookingDetails;
import com.sample.restfulbooker.objects.api.Token;
import com.sample.restfulbooker.utilities.Loggers;
import io.restassured.response.Response;

public class BookingData {
    BookingBuilders bookingBuilders = new BookingBuilders();
    BookingEndpointCalls bookingEndpointCalls = new BookingEndpointCalls();

    public Booking createNewBooking() {
        Booking booking = null;

        try {
            BookingDetails bookingRequestBody = bookingBuilders.createBookingWithFullData();
            Response response = bookingEndpointCalls.postRequestWithReportingForCreateBooking(bookingRequestBody);

            booking = response.getBody().as(Booking.class);
        } catch (Exception e) {
            Loggers.error("Unable to create a random booking. Cause: " + e.getMessage());
        }

        return booking;
    }

    public String generateToken() {
        String token = null;

        try {
            Token tokenAuthRequestBody = bookingBuilders.getTokenAuth();

            token = bookingEndpointCalls.postRequestAuth(tokenAuthRequestBody);
        } catch (Exception e) {
            Loggers.error("Unable to generate token. Cause: " + e.getMessage());
        }

        return token;
    }
}