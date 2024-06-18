package com.BookMyShow.Theatre.shows;

import com.BookMyShow.Theatre.enums.SeatType;

public record ShowSeatResponse(
        int id,
        String seatNo,
        SeatType seatType,
        double price
) {
}
