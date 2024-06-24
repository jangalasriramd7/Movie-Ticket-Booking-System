package com.BookMyShow.Theatre.shows;

import com.BookMyShow.Theatre.Exceptions.ShowNotAvailableException;
import com.BookMyShow.Theatre.Exceptions.TheatreNotFoundException;
import com.BookMyShow.Theatre.enums.SeatType;
import com.BookMyShow.Theatre.enums.ShowStatus;
import com.BookMyShow.Theatre.movie.MovieClient;
import com.BookMyShow.Theatre.movie.MovieResponse;
import com.BookMyShow.Theatre.theatre.Theatre;
import com.BookMyShow.Theatre.theatre.TheatreSeat;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.BookMyShow.Theatre.theatre.TheatreRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowService {

    private final TheatreRepository theatreRepository;

    private final MovieClient movieClient;

    private final ShowsRepository showsRepository;

    private final ShowSeatRepository showSeatRepository;

    public String addShow(ShowRequest showRequest) throws TheatreNotFoundException {

        Shows show = new Shows();

        MovieResponse movieResponse = movieClient.getMovieById(showRequest.movieId()).getBody();
        log.info("Inside the ShowService getting MovieName and MovieId: {} - {}", movieResponse, movieResponse.movieId());
        Optional<Theatre> optionalTheatre = theatreRepository.findById(showRequest.theatreId());

        if(optionalTheatre.isEmpty()){
            throw new TheatreNotFoundException("No Theatre found with the given ID");
        }

        Theatre theatre = optionalTheatre.get();

        List<TheatreSeat> theatreSeats = theatre.getTheatreSeat();
        System.out.println("Checking the length of the theatreSeats.size : " + theatreSeats.size());
        List<ShowSeat> showSeats = new ArrayList<>();
        for(TheatreSeat ts : theatreSeats){
            System.out.println("Seats in theatre");
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShows(show);
            showSeat.setSeatNo(ts.getSeatNo());
            showSeat.setSeatType(ts.getSeatType());
            showSeat.setAvailable(true);
            if(ts.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showRequest.classicPrice());
            }
            else{
                showSeat.setPrice(showRequest.premiumPrice());
            }
            showSeats.add(showSeat);
        }

        show.setTheatre(theatre);
        show.setMovieId(movieResponse.movieId());
        show.setShowTime(showRequest.showTime());
        show.setShowDate(showRequest.showDate());
        show.setShowStatus(ShowStatus.NOT_STARTED_YET);
        show.setShowSeatList(showSeats);
        theatre.getShows().add(show);
        showsRepository.save(show);
        System.out.println("Printing the showSeat list : ");

        return "Show has been successfully added";
    }

    public void fillShowTickets(Map<Integer, List<String>> map) throws ShowNotAvailableException {
        int showId = map.keySet().iterator().next();
        List<ShowSeat> seats = getShowSeats(showId).stream().map(ShowSeatMapper::toShowSeat).collect(Collectors.toList());
        List<String> requestedSeats = map.get(showId);
        System.out.println("Going fine and expected...");
        for(int i = 0; i < seats.size(); i++){
            for(int j = 0; j < requestedSeats.size(); j++){
                if(seats.get(i).equals(requestedSeats.get(j))){
                    seats.get(i).setAvailable(false);
                }
            }
            showSeatRepository.save(seats.get(i));
        }
    }

    public List<ShowSeatResponse> getShowSeats(int showId) throws ShowNotAvailableException {
        Optional<Shows> show = showsRepository.findById(showId);
        if(show.isEmpty()){
            throw new ShowNotAvailableException("Show is not available with the given Id");
        }
        System.out.println("Trying to print the show seats");

        return show.get().getShowSeatList()
                .stream()
                .map(ShowSeatMapper::fromShowSeat)
                .toList();
    }

    public ShowResponse findShowById(int showId){
        var response = showsRepository.findById(showId).map(ShowMapper::fromShow)
                .orElseThrow(() -> new EntityNotFoundException("Show i not present with the given Id : "));
        return new ShowResponse(response.showId(),
                response.theatreId(),
                response.movieId(),
                response.showTime(),
                response.showDate(),
                response.showStatus());
    }
}
