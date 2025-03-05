package com.example.javaspringjpamysql;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User userToUpdate) {
        return userRepository.findById(userToUpdate.getId()).map(u ->
        {
            u.setName(userToUpdate.getName());
            u.setEmail(userToUpdate.getEmail());
            return userRepository.save(u);
        }).orElse(null);
    }

}
