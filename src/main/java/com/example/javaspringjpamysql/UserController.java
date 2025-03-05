package com.example.javaspringjpamysql;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User newUser) {
        return ResponseEntity.ok(userService.addUser(newUser));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid User userToUpdate) {
        return ResponseEntity.ok(userService.updateUser(userToUpdate));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody @Valid User userToDelete) {
        User user = userService.deleteUser(userToDelete);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }

}
