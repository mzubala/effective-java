package com.bottega.rental.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor(onConstructor = @__({@JsonCreator}))
@ToString
public class MovieDto {
    private final long id;
    private final String title;
    private final String type;
    private final String description;
}
