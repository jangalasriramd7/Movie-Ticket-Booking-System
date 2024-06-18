package com.BookMyShow.Ticket.user;

public record UserResponse(
        int id,
        String username,
        String contact,
        String email,
        String password
) {
}