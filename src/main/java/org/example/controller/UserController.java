package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.UserDTO;  // Import UserDTO
import org.example.model.User;
import org.example.service.UserService;  // Import UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;  // Use UserService instead of UserRepository

    @PostMapping
    public User createUser(@Valid @RequestBody UserDTO userDto) {
        // Convert UserDTO to User
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userService.createUser(user);  // Use service to create user
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);  // Use service to get user by id
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();  // Use service to get all users
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDto) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            return userService.createUser(user);  // Reuse createUser for simplicity
        } else {
            return null;  // Or handle with an exception
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);  // Use service to delete user
    }
}
