package com.BookMyShow.Theatre.shows;

import org.springframework.stereotype.Service;

@Service
public class ShowSeatMapper {

    public static ShowSeatResponse fromShowSeat(ShowSeat showSeat){
        return new ShowSeatResponse(
                showSeat.getId(),
                showSeat.getSeatNo(),
                showSeat.getPrice(),
                showSeat.isAvailable(),
                showSeat.getSeatType()
        );
    }

    public static ShowSeat toShowSeat(ShowSeatResponse response){
        return new ShowSeat().builder()
                .id(response.id())
                .seatNo(response.seatNo())
                .seatType(response.seatType())
                .isAvailable(true)
                .price(response.price())
                .build();
    }
}
