package com.BookMyShow.Ticket;

import java.util.List;

public record TicketRequest(
    int theatreId,
    int showId,
    int userId,
    List<String> requestedSeats
){}
