package com.bottega.rental;

import java.util.Optional;

class InmemoryMovieDescriptionsClient implements MovieDescriptionsRepository {

    @Override
    public Optional<MovieDescriptionsHttpClient.MovieDescription> getByMovieId(MovieId id) {
        return Optional.of(new MovieDescriptionsHttpClient.MovieDescription("lorem ipsum"));
    }
}
