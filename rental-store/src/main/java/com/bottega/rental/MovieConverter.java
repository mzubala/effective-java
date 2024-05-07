package com.bottega.rental;

import com.bottega.rental.api.MovieAddRequest;
import com.bottega.rental.api.MovieDto;
import com.bottega.rental.api.MovieRentRequest;
import com.bottega.rental.api.MovieReturnRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
class MovieConverter {

    public static Movie from(MovieAddRequest request) {
        return new Movie(
          new MovieId(request.getId()),
          request.getTitle(),
          MovieType.valueOf(request.getType()));
    }

    public static MovieDto from(Movie request, MovieDescriptionsHttpClient.MovieDescription description) {
        return new MovieDto(
          request.getId().getId(),
          request.getTitle(),
          request.getType().toString(),
          description.description());
    }

    public static Rental from(MovieRentRequest request) {
        return new Rental(Rental.RentalType.RENT, request.accountId(), request.movieTitle());
    }

    public static Rental from(MovieReturnRequest request) {
        return new Rental(Rental.RentalType.RETURN, request.accountId(), request.movieTitle());
    }
}
