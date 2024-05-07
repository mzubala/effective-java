package com.bottega.rental;

import com.bottega.rental.api.MovieAddRequest;
import com.bottega.rental.api.MovieDto;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UnitTest {

    @RepeatedTest(10000)
    void shouldAddMovie() throws Exception {
        var movieAddRequest = new MovieAddRequest(42, "Spiderman", "NEW");
        MovieRentalFacade rental = instance();
        rental.addMovie(movieAddRequest);

        Optional<MovieDto> result = rental.getMovieById(movieAddRequest.getId());

        assertThat(result).isNotEmpty();
    }

    public static MovieRentalFacade instance() {
        return new MovieRentalFacade(
          new InmemoryMovieRepository(),
          new TypeBasedMoviePriceCalculator(1, 2, 3),
          new InmemoryMovieDescriptionsClient(),
          new InmemoryRentalRepository(),
          new InmemoryMessagePublisher());
    }
}
