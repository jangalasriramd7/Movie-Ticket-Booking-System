package com.BookMyShow.Movie.Exceptions;

public class MovieAlreadyExistsException extends Exception{
    public MovieAlreadyExistsException(String message){
        super(message);
    }
}
