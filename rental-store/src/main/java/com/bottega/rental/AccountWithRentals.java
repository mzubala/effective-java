package com.bottega.rental;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@RequiredArgsConstructor
class AccountWithRentals {

    private final String accountId;

    private final List<String> currentlyRented = new ArrayList<>();

    public void process(Rental rental) {
        switch (rental.type()) {
            case RETURN -> currentlyRented.remove(rental.movieTitle());
            case RENT -> currentlyRented.add(rental.movieTitle());
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public int currentRentals() {
        return currentlyRented.size();
    }

    public boolean isCurrentlyRented(String movieTitle) {
        return currentlyRented.contains(movieTitle);
    }
}
