package com.example.javaspringjpamysql;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

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

    public List<User> getUserByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public User updateUser(User userToUpdate) {
        return userRepository.findById(userToUpdate.getId()).map(u ->
        {
            u.setName(userToUpdate.getName());
            u.setEmail(userToUpdate.getEmail());
            return userRepository.save(u);
        }).orElse(null);
    }

    public User deleteUser(User userToDelete) {
        User user = userRepository.findByIdAndNameAndEmail(userToDelete.getId(), userToDelete.getName(), userToDelete.getEmail());
        if (user != null) {
            userRepository.delete(userToDelete);
        }
        return user;
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
