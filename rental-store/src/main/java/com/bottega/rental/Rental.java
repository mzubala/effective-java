package com.bottega.rental;

record Rental(RentalType type, String accountId, String movieTitle) {

    enum RentalType {
        RETURN, RENT
    }
}


