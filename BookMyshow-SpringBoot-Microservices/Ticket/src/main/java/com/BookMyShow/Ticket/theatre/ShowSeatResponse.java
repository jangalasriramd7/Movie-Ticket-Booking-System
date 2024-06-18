package com.BookMyShow.Ticket.theatre;

import com.BookMyShow.Ticket.enums.SeatType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ShowSeatResponse(
        int id,
        String seatNo,
        double price,
        boolean isAvailable,
        SeatType seatType
) {
}
