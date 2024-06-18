package com.BookMyShow.Movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record MovieRequest(
        @NotNull(message = "Movie Name can't be null")
        @NotBlank(message = "Movie Name can't be blank")
        @NotEmpty(message = "Movie Name can't be Empty")
        String movieName,
        double duration,
        double rating,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate releaseDate,
        Language language
) {
}
