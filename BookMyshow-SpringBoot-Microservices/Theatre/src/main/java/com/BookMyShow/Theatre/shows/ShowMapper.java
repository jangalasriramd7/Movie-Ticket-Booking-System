package com.BookMyShow.Theatre.shows;

import org.springframework.stereotype.Service;

@Service
public class ShowMapper {
    public static ShowResponse fromShow(Shows shows){
        return new ShowResponse(
                shows.getId(),
                shows.getTheatre().getId(),
                shows.getMovieId(),
                shows.getShowTime(),
                shows.getShowDate(),
                shows.getShowStatus()
        );
    }

//    public static Shows toShow(ShowResponse response){
//        return Shows.builder()
//                .id(response.showId())
//                .theatre(response.theatreId())
//                .showDate(response.showDate())
//                .showTime(response.showTime())
//                .showStatus(response.showStatus())
//                .showSeatList(response.)
//    }
}
