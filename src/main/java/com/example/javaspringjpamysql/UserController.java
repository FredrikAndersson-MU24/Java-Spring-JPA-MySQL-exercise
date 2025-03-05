package com.example.javaspringjpamysql;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
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
    public ResponseEntity<User> getUserById(@PathVariable long id) throws Exception {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else throw new EntityNotFoundException();
    }

    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        List<User> users = userService.getUserByName(name);
        if (!users.isEmpty()) {
            return users;
        } else throw new EntityNotFoundException();
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
        } else return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }

}
