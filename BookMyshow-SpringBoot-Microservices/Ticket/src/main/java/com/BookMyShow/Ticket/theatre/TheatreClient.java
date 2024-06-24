package com.BookMyShow.Ticket.theatre;

import com.BookMyShow.Ticket.Exceptions.BusinessException;
import com.BookMyShow.Ticket.movie.MovieResponse;
import com.BookMyShow.Ticket.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@FeignClient(
        name = "theatre-service",
        url = "${application.config.theatre-url}"
)
public interface TheatreClient {
    @GetMapping("/{theatreId}")
    ResponseEntity<TheatreResponse> findTheatreById(@PathVariable("theatreId")int id);

    @GetMapping("/shows/{showId}")
    ResponseEntity<ShowResponse> findShowById(@PathVariable("showId")int id);

    @GetMapping("/shows/showSeats/{show-id}")
    List<ShowSeatResponse> getShowSeats(@PathVariable("show-id") @Valid int showId) throws BusinessException;

    @PostMapping("/shows/fillTickets")
    ShowResponse fillShowTickets(Map<Integer, List<String>> requestBody) throws BusinessException;

}
