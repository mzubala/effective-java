package com.bottega.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
class RentalStoreDevConfiguration {

    @Bean
    @Profile("dev")
    @Primary
    MovieDescriptionsRepository inmemmovieDescriptions() {
        return new InmemoryMovieDescriptionsClient();
    }

    @Bean
    @Profile("dev")
    @Primary
    MoviePriceCalculator fixedmoviePriceCalculator() {
        return new TypeBasedMoviePriceCalculator(12, 10, 8);
    }

    @Bean
    @Profile("dev")
    @Primary
    MessagePublisher inmemrabbitMQMessagePublisher() {
        return new InmemoryMessagePublisher();
    }

    @Bean
    @Profile("dev")
    @Primary
    MovieRepository inmemmovieRepository() {
        return new InmemoryMovieRepository();
    }

    @Bean
    @Profile("dev")
    @Primary
    RentalRepository inmemrentalRepository() {
        return new InmemoryRentalRepository();
    }
}
