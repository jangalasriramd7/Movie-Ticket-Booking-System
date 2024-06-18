package com.BookMyShow.Theatre.movie;


import jakarta.validation.constraints.NotNull;

public record MovieRequest(
        @NotNull(message = "Movie id can't be null")
        int id,
        @NotNull(message = "Movie name can't be null")
        String name
) {
}
