package com.bottega.rental;

import java.util.Optional;

interface MovieDescriptionsRepository {
    Optional<MovieDescriptionsHttpClient.MovieDescription> getByMovieId(MovieId id);
}
