package com.BookMyShow.User;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(UserRequest request){
        if(request == null){
            return null;
        }
        return User.builder()
                .userName(request.userName())
                .contact(request.contact())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public UserResponse fromUser(User user){
        if(user == null){
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getContact(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
