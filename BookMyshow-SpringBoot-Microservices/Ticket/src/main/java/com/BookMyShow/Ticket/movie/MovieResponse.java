package com.BookMyShow.Ticket.movie;

import com.BookMyShow.Ticket.enums.Language;

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
