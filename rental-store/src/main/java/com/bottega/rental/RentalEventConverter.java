package com.bottega.rental;

import com.bottega.rental.api.MovieRentRequest;
import com.bottega.rental.api.MovieReturnRequest;
import com.bottega.rental.api.event.RentalEvent;
import lombok.experimental.UtilityClass;

@UtilityClass
class RentalEventConverter {

    public static RentalEvent from(MovieRentRequest request) {
        return new RentalEvent(RentalEvent.RentalEventType.RENT, request.accountId(), request.movieTitle());
    }

    public static RentalEvent from(MovieReturnRequest request) {
        return new RentalEvent(RentalEvent.RentalEventType.RETURN, request.accountId(), request.movieTitle());
    }
}
