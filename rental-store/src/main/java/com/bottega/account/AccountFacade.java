package com.bottega.account;

import com.bottega.rental.MovieRentalFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountFacade {
    private final MovieRentalFacade movieRentalFacade;
}
