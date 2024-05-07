package com.bottega.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@RequiredArgsConstructor
class JdbcRentalRepository implements RentalRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Rental rental) {
        jdbcTemplate.update("INSERT INTO rentals(event_type, account_id, movie_title) VALUES(?, ?, ?) ",
          rental.type().toString(), rental.accountId(), rental.movieTitle());
    }

    @Override
    public List<Rental> findAll() {
        return jdbcTemplate.query("SELECT * FROM rentals ORDER BY id", toRental());
    }

    @Override
    public List<Rental> findAllForAccount(String accountId) {
        return jdbcTemplate.query("SELECT * FROM rentals r WHERE r.account_id = ? ORDER BY id", toRental(), accountId);
    }

    static RowMapper<Rental> toRental() {
        return (rs, rowNum) -> new Rental(Rental.RentalType.valueOf(rs.getString("event_type")), rs.getString("account_id"), rs.getString("movie_title"));
    }
}
