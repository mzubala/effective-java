package com.bottega.rental;

import com.bottega.rental.api.MovieAddRequest;
import com.bottega.rental.api.MovieDto;
import com.bottega.rental.api.MovieRentRequest;
import com.bottega.rental.api.MovieReturnRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class MovieRentalFacade {

    private final MovieRepository movieRepository;
    private final MoviePriceCalculator moviePriceCalculator;
    private final MovieDescriptionsRepository movieDescriptionsHttpClient;
    private final RentalRepository rentalRepository;
    private final MessagePublisher messagePublisher;

    public Collection<MovieDto> getMovies() {
        return movieRepository.findAll().stream().map(toMovieDto()).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByType(String type) {
        return movieRepository.findAllByType(MovieType.valueOf(type)).stream().map(toMovieDto())
          .collect(Collectors.toList());
    }

    public Optional<MovieDto> getMovieById(long id) {
        return movieRepository.findOneById(new MovieId(id)).map(toMovieDto());
    }

    public void addMovie(MovieAddRequest movieAddRequest) {
        movieRepository.save(MovieConverter.from(movieAddRequest));
    }

    public void rentMovie(MovieRentRequest request) {
        AccountWithRentals aggregated = aggregateRentalsForAccount(request.accountId());

        log.info(aggregated.toString());

        if (aggregated.currentRentals() == 10) {
            throw new IllegalStateException("Can't rent more than 10 movies!");
        }

        if (aggregated.isCurrentlyRented(request.movieTitle())) {
            throw new IllegalStateException("Movie %s is already rented!".formatted(request.movieTitle()));
        }

        // TODO solve dual-write
        rentalRepository.save(MovieConverter.from(request));
        messagePublisher.publish(RentalEventConverter.from(request));
    }

    private AccountWithRentals aggregateRentalsForAccount(String accountId) {
        AccountWithRentals aggregated = new AccountWithRentals(accountId);
        rentalRepository.findAllForAccount(accountId).forEach(aggregated::process);
        return aggregated;
    }

    public void returnMovie(MovieReturnRequest request) {
        AccountWithRentals aggregated = aggregateRentalsForAccount(request.accountId());

        log.info(aggregated.toString());

        if (!aggregated.isCurrentlyRented(request.movieTitle())) {
            throw new IllegalStateException("Movie %s is not rented by %s".formatted(request.movieTitle(), request.accountId()));
        }

        // TODO solve dual-write
        rentalRepository.save(MovieConverter.from(request));
        messagePublisher.publish(RentalEventConverter.from(request));
    }

    private Function<Movie, MovieDto> toMovieDto() {
        return movie -> MovieConverter.from(movie, movieDescriptionsHttpClient.getByMovieId(movie.getId())
          .orElse(null));
    }
}
