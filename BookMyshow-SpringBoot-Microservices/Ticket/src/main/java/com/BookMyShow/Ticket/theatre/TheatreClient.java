package com.BookMyShow.Ticket.theatre;

import com.BookMyShow.Ticket.movie.MovieResponse;
import com.BookMyShow.Ticket.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "theatre-service",
        url = "${application.config.theatre-url}"
)
public interface TheatreClient {
    @GetMapping("/{theatre-id}")
    Optional<TheatreResponse> findTheatreById(@PathVariable("theatre-id")int id);

    @GetMapping("/{show-id}")
    Optional<ShowResponse> findShowById(@PathVariable("showId")int id);

}
