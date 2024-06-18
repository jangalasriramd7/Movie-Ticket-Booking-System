package com.BookMyShow.User;

public record UserResponse(
        int id,
        String username,
        String contact,
        String email,
        String password
) {
}
