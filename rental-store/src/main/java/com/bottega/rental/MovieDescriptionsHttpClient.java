package com.bottega.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RequiredArgsConstructor
class MovieDescriptionsHttpClient implements MovieDescriptionsRepository {

    private final String URL;

    private final RestTemplate restTemplate;

    @Override
    public Optional<MovieDescription> getByMovieId(MovieId id) {
        var response = restTemplate.getForEntity(URL + "/movie-descriptions/{movieId}", MovieDescription.class, id.getId());

        return switch (response.getStatusCode().value()) {
            case 404 -> Optional.empty();
            default -> Optional.ofNullable(response.getBody());
        };
    }

    public record MovieDescription(String description) {
    }
}
