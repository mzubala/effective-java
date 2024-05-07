package com.bottega.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
class MovieDetailsServiceHealthCheck implements HealthIndicator {

    private final String URL;
    private final RestTemplate restTemplate;

    @Override
    public Health health() {
        try {
            var response = restTemplate.getForEntity(URL + "/actuator/health", String.class);
            return response.getStatusCode().is2xxSuccessful()
              ? Health.up().build()
              : Health.down().status(response.getStatusCode().toString()).build();
        } catch (RestClientException e) {
            return Health.down(e).build();
        }
    }
}
