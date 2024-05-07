package com.bottega.rental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class InmemoryRentalRepository implements RentalRepository {

    private final List<Rental> rentals = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void save(Rental rental) {
        rentals.add(rental);
    }

    @Override
    public List<Rental> findAll() {
        return rentals;
    }

    @Override
    public List<Rental> findAllForAccount(String accountId) {
        return rentals.stream()
          .filter(r -> r.accountId().equals(accountId))
          .collect(Collectors.toList());
    }
}
