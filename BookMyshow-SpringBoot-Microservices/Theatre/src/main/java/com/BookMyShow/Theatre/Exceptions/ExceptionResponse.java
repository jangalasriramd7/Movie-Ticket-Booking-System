package com.BookMyShow.Theatre.Exceptions;

import java.util.*;

public record ExceptionResponse(
    Map<String, String> errors){
}
