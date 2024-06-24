package com.BookMyShow.Ticket.theatre;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TheatreResponse(
        int theatreId,
        String theatreName,
        String theatreRegion,
        @JsonProperty("showList")
        List<ShowResponse> showList
) {
}
