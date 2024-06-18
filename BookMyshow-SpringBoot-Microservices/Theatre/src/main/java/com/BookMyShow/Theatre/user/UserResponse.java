package com.BookMyShow.Theatre.user;

public record UserResponse(
        int userId,
        String username,
        String contact,
        String email,
        String password
){
}
