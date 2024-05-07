package com.movies.descriptions.moviedescriptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieDescriptionsController {

    @GetMapping("/movie-descriptions/{movieId}")
    public ResponseEntity<Description> getDescription(@PathVariable int movieId) {
        var description = switch (movieId) {
            case 1 -> new Description("Miles Miles Morales embarks on a thrilling adventure through the multiverse and joins forces with Gwen Stacy and Spider-People to fight a supervillain."); // Spiderman
            case 2 -> new Description("When a few objects that can be manipulated and used as weapons in the future fall into the wrong hands, a CIA operative, known as the Protagonist, must save the world."); // Tenet
            case 3 -> new Description("During WWII, Rick, a nightclub owner in Casablanca, agrees to help his former lover Ilsa and her husband. Soon, Ilsa's feelings for Rick resurface and she finds herself renewing her love for him."); // Casablanca
            default -> null;
        };

        return description != null
          ? ResponseEntity.ok().body(description)
          : ResponseEntity.notFound().build();
    }

    public record Description(String description) {
    }
}
