package com.BookMyShow.Theatre.theatre;

public record TheatreRequest(
        int id,
        String theatreName,
        String theatreRegion,
        int rows,
        int columns
) {
}
