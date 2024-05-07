CREATE TABLE rentals
(
    id          SERIAL PRIMARY KEY,
    event_type  TEXT NOT NULL,
    account_id  TEXT NOT NULL,
    movie_title TEXT NOT NULL
);
