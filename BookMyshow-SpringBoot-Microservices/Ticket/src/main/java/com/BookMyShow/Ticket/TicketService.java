package com.BookMyShow.Ticket;

import com.BookMyShow.Ticket.Exceptions.*;

import com.BookMyShow.Ticket.movie.MovieClient;
import com.BookMyShow.Ticket.movie.MovieResponse;
import com.BookMyShow.Ticket.theatre.*;
import com.BookMyShow.Ticket.user.UserClient;
import com.BookMyShow.Ticket.user.UserResponse;
import com.BookMyShow.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketService{

    private final UserClient userClient;

    private final TicketRepository ticketRepository;

    private final TheatreClient theatreClient;

    private final MovieClient movieClient;

    private final ShowClient showClient;

    public TicketResponse bookTicket(TicketRequest ticketRequest) throws RequestedSeatsNotAvailableException, UserNotFoundException, ShowNotAvailableException, TheatreNotFoundException, BusinessException, MovieNotFoundException {

        Optional<TheatreResponse> optionalTheatreResponse = theatreClient.findTheatreById(ticketRequest.theatreId());
        Optional<UserResponse> optionalUserResponse = userClient.findUserById(ticketRequest.userId());
        Optional<ShowResponse> optionalShow = theatreClient.findShowById(ticketRequest.showId());
        if(optionalTheatreResponse.isEmpty()){
            throw new TheatreNotFoundException("The Given theatre id is not valid. No theatre present with the given theatre Id");
        }
        if(optionalUserResponse.isEmpty()){
            throw new UserNotFoundException("The Given user id is not valid. No user exists with this ID");
        }
        if(optionalShow.isEmpty()){
            throw new ShowNotAvailableException("the requested Show ID is not valid. No Show exists with this Show ID");
        }
        TheatreResponse theatre = optionalTheatreResponse.get();
        List<ShowResponse> shows = theatre.shows();
        ShowResponse show = optionalShow.get();
        boolean showCheck = shows.stream().anyMatch(i -> i.showId() == show.showId());
        if(!showCheck){
            System.out.println("Requested Show ID is not present");
            throw new ShowNotAvailableException("the requested Show ID is not valid. No Show exists with this Show ID in the given theatre");
        }
        System.out.println("Show Available : " + showCheck);
        UserResponse user = optionalUserResponse.get();
        double totalPrice = validateShowAvailability(show, ticketRequest.requestedSeats());
        String message = "Your tickets got booked in " + theatre.theatreName() + " for " + show.showTime() +
                " on " + show.showDate() + ". Have a great time\n\n" + "Total : " + totalPrice;

        Ticket ticket = Ticket.builder()
                .bookingStatus(BookingStatus.BOOKING_SUCCESSFUL)
                .showSeats(ticketRequest.requestedSeats())
                .showId(show.showId())
                .userId(user.id())
                .createdAt(LocalDateTime.now())
                .build();

//        show.ticketList().add(ticket);
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(show.showId(), ticketRequest.requestedSeats());
        showClient.fillShowTickets(map);
        ticketRepository.save(ticket);
        return createTicketResponse(theatre, show, ticket, message, totalPrice);
    }


    public double validateShowAvailability(ShowResponse show, List<String> requestedSeats) throws RequestedSeatsNotAvailableException, BusinessException {
        double totalPrice = 0;
        List<ShowSeatResponse> showSeatList = showClient.getShowSeats();
        List<ShowSeatResponse> validSeats = new ArrayList<>();
        for (ShowSeatResponse showSeat : showSeatList) {
            for(String seat : requestedSeats) {
                if(showSeat.seatNo().equals(seat) && showSeat.isAvailable()){
                    validSeats.add(showSeat);
                }
                if (showSeat.seatNo().equals(seat) && !showSeat.isAvailable()){
                    throw new RequestedSeatsNotAvailableException("The Requested seat " + showSeat.seatNo() + " are not available");
                }

            }
        }

        totalPrice = calculatePrice(validSeats);
        return totalPrice;
    }

    public double calculatePrice(List<ShowSeatResponse> seats){
        double totalAmount = 0;
        for(ShowSeatResponse seat : seats){
            totalAmount += seat.price();
        }
        return totalAmount;
    }
    public TicketResponse createTicketResponse(TheatreResponse theatre, ShowResponse show, Ticket ticket, String message, double totalPrice) throws MovieNotFoundException {
        StringBuilder seats = new StringBuilder();
        for(String s : ticket.getShowSeats()){
            seats.append(s).append(", ");
        }
        MovieResponse movieResponse = movieClient.findMovieById(show.movieId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        seats.deleteCharAt(seats.length() - 2);

        return new TicketResponse(
                message,
                show.showTime(),
                show.showDate(),
                theatre.theatreName(),
                movieResponse.movieName(),
                seats.toString(),
                theatre.theatreRegion(),
                totalPrice
        );
    }
}
