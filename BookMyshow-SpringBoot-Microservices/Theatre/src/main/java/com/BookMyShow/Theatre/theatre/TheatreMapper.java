package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.shows.ShowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheatreMapper {

    private final ShowMapper showMapper;
    public static TheatreResponse fromTheatre(Theatre theatre){
        return new TheatreResponse(
                theatre.getId(),
                theatre.getName(),
                theatre.getRegion(),
                theatre.getShows()
                        .stream()
                        .map(ShowMapper::fromShow)
                        .toList()
        );
    }
}
