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

    public TicketResponse bookTicket(TicketRequest ticketRequest) throws RequestedSeatsNotAvailableException, UserNotFoundException, ShowNotAvailableException, TheatreNotFoundException, BusinessException, MovieNotFoundException {

        TheatreResponse theatreResponse = theatreClient.findTheatreById(ticketRequest.theatreId()).getBody();
        UserResponse userResponse = userClient.findUserById(ticketRequest.userId()).getBody();
        ShowResponse showResponse = theatreClient.findShowById(ticketRequest.showId()).getBody();
        System.out.println("UserResponse in Ticket : " + userResponse);
        System.out.println("TheatreResponse in Ticket : " + theatreResponse);
        System.out.println("ShowResponse in Ticket : " + showResponse);
        List<ShowResponse> shows = theatreResponse.showList();
        boolean showCheck = shows.stream().anyMatch(i -> i.showId() == showResponse.showId());
        if(!showCheck){
            System.out.println("Requested Show ID is not present");
            throw new ShowNotAvailableException("the requested Show ID is not valid. No Show exists with this Show ID in the given theatre");
        }
        System.out.println("Show Available : " + showCheck);

        double totalPrice = validateShowAvailability(showResponse, ticketRequest.requestedSeats());
        String message = "Your tickets got booked in " + theatreResponse.theatreName() + " for " + showResponse.showTime() +
                " on " + showResponse.showDate() + ". Have a great time\n\n" + "Total : " + totalPrice;

        Ticket ticket = Ticket.builder()
                .bookingStatus(BookingStatus.BOOKING_SUCCESSFUL)
                .showSeats(ticketRequest.requestedSeats())
                .showId(showResponse.showId())
                .userId(userResponse.id())
                .createdAt(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

//        show.ticketList().add(ticket);
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(showResponse.showId(), ticketRequest.requestedSeats());
        theatreClient.fillShowTickets(map);
        ticketRepository.save(ticket);
        return createTicketResponse(theatreResponse, showResponse, ticket, message, totalPrice);
    }


    public double validateShowAvailability(ShowResponse show, List<String> requestedSeats) throws RequestedSeatsNotAvailableException, BusinessException {
        double totalPrice = 0;
        List<ShowSeatResponse> showSeatList = theatreClient.getShowSeats(show.showId());
        List<ShowSeatResponse> validSeats = new ArrayList<>();
        System.out.println("ShowSeatList.size : " + showSeatList.size() + ", requestedSeats.size() : " + requestedSeats.size() + ", --> " + requestedSeats);
        for (ShowSeatResponse showSeat : showSeatList) {
            for(String seat : requestedSeats) {
                System.out.println("Seat No. " + seat + ", showSeatNo. " + showSeat.seatNo() + ", showSeat.isAvailable : " + showSeat.isAvailable());
                if(showSeat.seatNo().equals(seat) && showSeat.isAvailable()){
                    validSeats.add(showSeat);
                }
                if (showSeat.seatNo().equals(seat) && !showSeat.isAvailable()){
                    throw new RequestedSeatsNotAvailableException("The Requested seat " + showSeat.seatNo() + " are not available");
                }
            }
        }
        System.out.println("validSeats.size() : " + validSeats.size() + ", Printing the cost of seats : ");
        validSeats.stream().map(ShowSeatResponse::price).forEach(System.out::println);
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
        MovieResponse movieResponse = movieClient.findMovieById(show.movieId()).getBody();
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
