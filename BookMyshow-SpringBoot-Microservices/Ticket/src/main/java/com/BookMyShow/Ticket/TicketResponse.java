package com.BookMyShow.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public record TicketResponse(
    String responseMessage,
    LocalTime showTime,
    LocalDate showDate,
    String theatreName,
    String movieName,
    String bookedSeats,
    String location,
    double totalPrice) {}
