package com.BookMyShow.Movie;

import java.time.LocalDate;

public record MovieResponse(
        int movieId,
        String movieName,
        double duration,
        double rating,
        LocalDate releaseDate,
        Language language
) {
}
