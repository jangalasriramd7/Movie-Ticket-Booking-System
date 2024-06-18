package com.BookMyShow.Ticket.theatre;

import java.util.List;

public record TheatreResponse(
        String theatreName,
        String theatreRegion,
        List<ShowResponse> shows
) {
}
