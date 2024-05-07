package com.bottega.rental.api.event;

public record RentalEvent(RentalEventType eventType, String accountId, String movieTitle) {

    public enum RentalEventType {
        RENT, RETURN
    }

}
