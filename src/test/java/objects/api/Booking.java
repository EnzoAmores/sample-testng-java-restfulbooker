package objects.api;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import lombok.Getter;

@Builder
@Getter
@Jacksonized
public class Booking {
    private Integer bookingid;
    private BookingDetails booking;
}