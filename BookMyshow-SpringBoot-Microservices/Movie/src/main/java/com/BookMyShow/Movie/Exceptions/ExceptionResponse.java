package com.BookMyShow.Movie.Exceptions;

import java.util.*;

public record ExceptionResponse(
        Map<String, String> errors
) {

}
