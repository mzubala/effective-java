package com.bottega.rental;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalStoreConfiguration {

    @Bean
    MovieDescriptionsRepository movieDescriptionsHttpClient(@Value("${rental.movie-descriptions-service.url}") String url, RestTemplateBuilder builder) {
        return new MovieDescriptionsHttpClient(url, builder.build());
    }

    @Bean
    MoviePriceCalculator moviePriceCalculator(@Value("${rental.price.new}") long priceNew, @Value("${rental.price.old}") long priceOld, @Value("${rental.price.regular}") long priceRegular) {
        return new TypeBasedMoviePriceCalculator(priceNew, priceOld, priceRegular);
    }

    @Bean
    MovieRentalFacade movieRentalFacade(MovieRepository movieRepository, MoviePriceCalculator moviePriceCalculator, MovieDescriptionsRepository movieDescriptionsHttpClient, RentalRepository rentalRepository, MessagePublisher messagePublisher
    ) {
        return new MovieRentalFacade(movieRepository, moviePriceCalculator, movieDescriptionsHttpClient, rentalRepository, messagePublisher);
    }
}
