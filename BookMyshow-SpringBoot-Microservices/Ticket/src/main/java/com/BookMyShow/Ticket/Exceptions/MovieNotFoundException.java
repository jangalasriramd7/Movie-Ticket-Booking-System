package com.BookMyShow.Ticket.Exceptions;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message){
        super(message);
    }
}
