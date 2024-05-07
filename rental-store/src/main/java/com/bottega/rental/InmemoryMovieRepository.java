package com.bottega.rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InmemoryMovieRepository implements MovieRepository {

    private final Map<MovieId, Movie> movies = new ConcurrentHashMap<>();

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public List<Movie> findAllByType(MovieType type) {
        return movies.values().stream()
          .filter(m -> m.getType() == type)
          .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findOneById(MovieId id) {
        return Optional.ofNullable(movies.get(id));
    }

    @Override
    public void save(Movie movie) {
        movies.put(movie.getId(), movie);
    }
}
