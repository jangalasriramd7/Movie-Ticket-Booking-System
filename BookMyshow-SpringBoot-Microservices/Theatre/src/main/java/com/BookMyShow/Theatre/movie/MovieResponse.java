package com.BookMyShow.Theatre.movie;

import com.BookMyShow.Theatre.enums.Language;

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
