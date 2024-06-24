package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.shows.ShowResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreResponse{
        int theatreId;
        String theatreName;
        String theatreRegion;
        @JsonProperty("showList")
        List<ShowResponse> showsList = new ArrayList<>();
}
