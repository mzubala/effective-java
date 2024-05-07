package com.bottega.rental.web;

import com.bottega.rental.MovieRentalFacade;
import com.bottega.rental.api.MovieAddRequest;
import com.bottega.rental.api.MovieDto;
import com.bottega.rental.api.MovieRentRequest;
import com.bottega.rental.api.MovieReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MovieRentalController {

    private final MovieRentalFacade movieService;

    @PostMapping("/movies/rent")
    public void rentMovie(@RequestBody MovieRentRequest movieRentRequest) {
        movieService.rentMovie(movieRentRequest);
    }

    @PostMapping("/movies/return")
    public void returnMovie(@RequestBody MovieReturnRequest movieReturnRequest) {
        movieService.returnMovie(movieReturnRequest);
    }

    @GetMapping("/movies")
    public List<MovieDto> movies(@RequestParam(required = false) String type) {
        if (type != null) {
            return movieService.getMoviesByType(type);
        }
        return new ArrayList<>(movieService.getMovies());
    }

    @GetMapping("/movies/{id}")
    public Optional<MovieDto> movieById(@PathVariable long id) {
        return movieService.getMovieById(id);
    }

    /*
    curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "id":14, "title":"spiderman", "type":"NEW"}' \
 http://localhost:8081/movies
     */

    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieAddRequest dto) {
        movieService.addMovie(dto);
    }
}
