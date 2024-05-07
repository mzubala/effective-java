package com.bottega.rental;

import java.util.List;
import java.util.Optional;

interface MovieRepository {


    List<Movie> findAll();

    List<Movie> findAllByType(MovieType type);

    Optional<Movie> findOneById(MovieId id);

    void save(Movie movie);
}
