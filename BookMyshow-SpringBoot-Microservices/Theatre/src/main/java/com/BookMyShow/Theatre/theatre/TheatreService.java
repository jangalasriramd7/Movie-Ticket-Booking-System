package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.Exceptions.TheatreAlreadyExistsException;
import com.BookMyShow.Theatre.Exceptions.TheatreNotFoundException;
import com.BookMyShow.Theatre.enums.SeatType;
import com.BookMyShow.Theatre.shows.ShowMapper;
import com.BookMyShow.Theatre.shows.ShowResponse;
import com.BookMyShow.Theatre.shows.ShowsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public List<TheatreResponse> getAllTheatresFromRegion(String regionName) {
        return theatreRepository.findByRegion(regionName).stream().map(TheatreMapper::fromTheatre).toList();
    }


    public TheatreResponse findTheatreById(int theatreId) throws TheatreNotFoundException {
        List<ShowResponse> showList = showsRepository.findByTheatreId(theatreId).stream().map(ShowMapper::fromShow).toList();
        System.out.println("Printing in theatreService shows : ");
        showList.forEach(System.out::println);
        Optional<Theatre> op = theatreRepository.findById(theatreId);
        if(op.isEmpty()){
            throw new TheatreNotFoundException("No theatre found with the given Id : " + theatreId);
        }
        TheatreResponse response = op.map(TheatreMapper::fromTheatre).get();
        response.setShowsList(showList);
        System.out.println("Printing in the theatreService : " + response.getTheatreId() + ", " + response.getTheatreName() + ", " + response.getTheatreRegion()
        + ", " + response.getShowsList());
        return response;

    }
}
