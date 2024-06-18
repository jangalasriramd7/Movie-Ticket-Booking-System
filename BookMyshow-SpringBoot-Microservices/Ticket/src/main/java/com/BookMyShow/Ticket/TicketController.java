package com.BookMyShow.Ticket;

import com.BookMyShow.Ticket.Exceptions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book_my_show/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody @Valid TicketRequest request) throws UserNotFoundException, TheatreNotFoundException, ShowNotAvailableException, RequestedSeatsNotAvailableException, BusinessException, MovieNotFoundException {
        TicketResponse ticketResponse = ticketService.bookTicket(request);
        return ResponseEntity.ok(ticketResponse);
    }
}
