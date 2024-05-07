package com.bottega.rental;

import com.bottega.rental.api.event.RentalEvent;

interface MessagePublisher {
    void publish(RentalEvent event);
}
