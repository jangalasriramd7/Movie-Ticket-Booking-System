package com.BookMyShow.Ticket.Exceptions;

public class RequestedSeatsNotAvailableException extends Exception{
    public RequestedSeatsNotAvailableException(String message){
        super(message);
    }
}
