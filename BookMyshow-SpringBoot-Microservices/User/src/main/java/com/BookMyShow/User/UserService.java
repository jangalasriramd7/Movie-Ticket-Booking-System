package com.BookMyShow.User;

import com.BookMyShow.User.Exceptions.UserAlreadyExistsException;
import com.BookMyShow.User.Exceptions.UserNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    public int createUser(UserRequest request) throws UserAlreadyExistsException {
        List<User> users = userRepository.findAll();
        boolean existing = users.stream().anyMatch(user -> user.getUserName().equals(request.userName()) || user.getEmail().equals(request.email()));
        if(existing){
            throw new UserAlreadyExistsException("User Already exists with the given name and email. Please try another");
        }

        var user = userRepository.save(mapper.toUser(request));
        return user.getId();
    }

    public UserResponse findById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(mapper::fromUser)
                .orElseThrow(() -> new UserNotFoundException("No User found with the given id"));
    }

    private void mergerUser(User customer, UserRequest request){
        if(StringUtils.isNotBlank(request.userName())){
            customer.setUserName(request.userName());
        }
        if(StringUtils.isNotBlank(request.contact())){
            customer.setContact(request.contact());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.password() != null){
            customer.setPassword(request.password());
        }
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(mapper::fromUser)
                .collect(Collectors.toList());
    }

    public void updateUser(int id, UserRequest request) throws UserNotFoundException {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid UserId: No user found with the given user id."));
        mergerUser(user, request);
        userRepository.save(user);
    }

    public Boolean existsById(int id){
        return userRepository.findById(id)
                .isPresent();
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
