package com.BookMyShow.Exceptions;

public class RequestedSeatsNotAvailableException extends Exception{
    public RequestedSeatsNotAvailableException(String message){
        super(message);
    }
}
