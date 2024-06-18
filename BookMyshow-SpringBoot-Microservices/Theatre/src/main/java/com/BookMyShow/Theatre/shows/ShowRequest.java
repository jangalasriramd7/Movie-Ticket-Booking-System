package com.BookMyShow.Theatre.shows;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowRequest(

        @JsonFormat(pattern="HH:mm:ss")
    LocalTime showTime,
    LocalDate showDate,
    int theatreId,
    int movieId,
    int classicPrice,
    int premiumPrice){
}
