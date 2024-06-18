package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.shows.Shows;

import java.util.List;

public record TheatreResponse(
        String theatreName,
        String theatreRegion,
        List<Shows> showsList
) {
}
