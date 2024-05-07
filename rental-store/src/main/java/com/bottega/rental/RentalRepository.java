package com.bottega.rental;

import java.util.List;

interface RentalRepository {
    void save(Rental rental);

    List<Rental> findAll();

    List<Rental> findAllForAccount(String accountId);
}
