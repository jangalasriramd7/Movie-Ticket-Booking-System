package com.BookMyShow.User;

import jakarta.validation.constraints.NotNull;


public record UserRequest(
        @NotNull(message = "username should not be blank")
        String userName,
        @NotNull(message = "contact should not be blank")
        String contact,
        @NotNull(message = "Email should not be blank")
        String email,
        @NotNull(message = "Password should not be blank")
        String password){
}
