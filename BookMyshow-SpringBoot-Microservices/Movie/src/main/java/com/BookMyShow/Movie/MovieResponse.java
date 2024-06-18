package com.BookMyShow.Movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record MovieResponse(
        String movieName,
        double duration,
        double rating,
        LocalDate releaseDate,
        Language language
) {
}
