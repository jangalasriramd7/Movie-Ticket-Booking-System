package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Exceptions.TheatreAlreadyExistsException;
import com.BookMyShow.Theatre.enums.SeatType;
import com.BookMyShow.Theatre.shows.Shows;
import com.BookMyShow.Theatre.shows.ShowsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final TheatreRepository theatreRepository;

    private final ShowsRepository showsRepository;

    public String addTheatre(TheatreRequest theatreRequest) throws TheatreAlreadyExistsException {
        String theatreName = theatreRequest.theatreName();
        String region = theatreRequest.theatreRegion();
        Optional<Theatre> theatreOp = theatreRepository.findByNameAndRegion(theatreName, region);
        if(theatreOp.isPresent()){
            throw new TheatreAlreadyExistsException("Theatre Already Exists with the given name in that region");
        }
        Theatre theatre = Theatre.builder()
                .name(theatreRequest.theatreName())
                .region(theatreRequest.theatreRegion())
                .shows(new ArrayList<>())
                .build();
        double rows = theatreRequest.rows();
        double columns = theatreRequest.columns();
        System.out.println("rows : " + rows + ", columns : " + columns);
        int premiumRows = (int)(0.3 * rows);
        List<TheatreSeat> seats = new ArrayList<>();

        for(char ch = 'A'; ch < 'A' + premiumRows; ch++){
            for(int i = 1; i <= columns; i++) {
                TheatreSeat theatreSeat = new TheatreSeat();
                String seat = ch + String.valueOf(ch) + i;
                theatreSeat.setSeatNo(seat);
                theatreSeat.setSeatType(SeatType.PREMIUM);
                theatreSeat.setTheatre(theatre);
                seats.add(theatreSeat);
            }

        }

        for(char c = 'A'; c < 'A' + rows - premiumRows; c++){
            for(int i = 1; i <= columns; i++) {
                TheatreSeat theatreSeat = new TheatreSeat();
                String seat = String.valueOf(c) + i;
                theatreSeat.setSeatNo(seat);
                theatreSeat.setSeatType(SeatType.CLASSIC);
                theatreSeat.setTheatre(theatre);
                seats.add(theatreSeat);
            }

        }
        System.out.println("seats : " + seats);
        theatre.setTheatreSeat(seats);
        theatreRepository.save(theatre);
        return "Theatre and Seats are added successfully into the theatre DB";
    }


    public List<Theatre> getAllTheatresFromRegion(String regionName) {
        return theatreRepository.findByRegion(regionName);
    }


    public List<Shows> getAllShowsOfTheatre(int theatreId) {
        return showsRepository.findByTheatreId(theatreId);
    }


//    public List<Theatre> getAllTheatresByMovieName(String movieName) {
//        List<Theatre> theatres = theatreRepository.findAll();
//        List<Theatre> movieTheatres = new ArrayList<>();
//        for(Theatre theatre : theatres){
//            Set<Shows> shows = theatre.getShows()
//                    .stream()
//                    .filter(show -> show.getMovieId()
//                            .equals(movieName))
//                    .collect(Collectors.toSet());
//
//            if(!shows.isEmpty()){
//                movieTheatres.add(theatre);
//            }
//        }
//        return movieTheatres;
//    }

    public void deleteTheatreById(int id){
        theatreRepository.deleteById((long)id);
    }
}
