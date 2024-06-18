package com.BookMyShow.Ticket.user;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"
)
public interface UserClient {
    @GetMapping("/{user-id}")
    Optional<UserResponse> findUserById(@PathVariable("user-id")int id);

}
