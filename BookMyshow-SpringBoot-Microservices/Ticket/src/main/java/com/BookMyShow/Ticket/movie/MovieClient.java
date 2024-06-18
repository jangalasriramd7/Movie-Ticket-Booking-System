package com.BookMyShow.Ticket.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "movie-service",
        url = "${application.config.movie-url}"
)
public interface MovieClient {
    @GetMapping("/{movieId}")
    Optional<MovieResponse> findMovieById(@PathVariable("movieId")int id);
}
