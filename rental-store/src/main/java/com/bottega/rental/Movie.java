package com.bottega.rental;

import java.util.Objects;

class Movie {

    private final MovieId id;
    private final String title;
    private final MovieType type;

    public Movie(MovieId id, String title, MovieType type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public MovieType getType() {
        return type;
    }

    public MovieId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
          Objects.equals(title, movie.title) &&
          type == movie.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type);
    }

    @Override
    public String toString() {
        return "Movie{" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", type=" + type +
          '}';
    }
}
