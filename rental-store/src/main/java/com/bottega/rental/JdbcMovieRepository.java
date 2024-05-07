package com.bottega.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class JdbcMovieRepository implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movie", toMovie());
    }

    @Override
    public List<Movie> findAllByType(MovieType type) {
        return jdbcTemplate.query("SELECT * FROM movie m WHERE m.type = ?", toMovie(), type.toString());
    }

    @Override
    public Optional<Movie> findOneById(MovieId id) {
        try {
            Movie movie = jdbcTemplate.queryForObject("SELECT * FROM movie m WHERE m.id = ?", toMovie(), id.getId());
            return Optional.ofNullable(movie);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Movie movie) {
        jdbcTemplate.update("INSERT INTO movie(id, title, type) VALUES(?, ?, ?)", movie.getId().getId(), movie.getTitle(), movie.getType().toString());
    }

    static RowMapper<Movie> toMovie() {
        return (rs, __) -> new Movie(
          new MovieId(rs.getLong("id")),
          rs.getString("title"),
          MovieType.valueOf(rs.getString("type")));
    }
}
