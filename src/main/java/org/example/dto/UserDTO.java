package org.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotNull(message = "Username cannot be null")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}