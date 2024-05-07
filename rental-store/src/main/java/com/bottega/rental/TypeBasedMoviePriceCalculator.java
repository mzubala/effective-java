package com.bottega.rental;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class TypeBasedMoviePriceCalculator implements MoviePriceCalculator {

    private final long priceNew;
    private final long priceOld;
    private final long priceRegular;

    @Override
    public long getPriceFor(Movie movie) {
        return switch (movie.getType()) {
            case NEW -> priceNew;
            case REGULAR -> priceRegular;
            case OLD -> priceOld;
        };
    }
}
