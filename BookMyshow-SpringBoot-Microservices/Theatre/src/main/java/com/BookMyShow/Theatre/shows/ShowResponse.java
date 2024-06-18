package com.BookMyShow.Theatre.shows;

import com.BookMyShow.Theatre.enums.ShowStatus;
import java.time.LocalDate;
import java.time.LocalTime;

public record ShowResponse(
        int showId,
        int theatreId,
        int movieId,
        LocalTime showTime,
        LocalDate showDate,
        ShowStatus showStatus
) {
}
