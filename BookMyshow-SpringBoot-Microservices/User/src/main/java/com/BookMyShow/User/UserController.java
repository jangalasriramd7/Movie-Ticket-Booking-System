package com.BookMyShow.User;

import com.BookMyShow.User.Exceptions.UserAlreadyExistsException;
import com.BookMyShow.User.Exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book_my_show/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody @Valid UserRequest request) throws UserAlreadyExistsException {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") int id, @RequestBody @Valid UserRequest request) throws UserNotFoundException {
        userService.updateUser(id, request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/exists/{user-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("user-id") int id){
        return ResponseEntity.ok(userService.existsById(id));
    }

    @DeleteMapping("/user-id")
    public ResponseEntity<Void> deleteById(@PathVariable("user-id") int id){
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }
}
