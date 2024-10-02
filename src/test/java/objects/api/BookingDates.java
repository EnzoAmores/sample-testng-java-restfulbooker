package objects.api;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import lombok.Getter;

@Builder
@Getter
@Jacksonized
public class BookingDates {
    private String checkin;
    private String checkout;
}