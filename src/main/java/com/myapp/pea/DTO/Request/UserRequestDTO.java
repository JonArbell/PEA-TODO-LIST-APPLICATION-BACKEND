package com.myapp.pea.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotNull(message = "Email is required.")
    @Email(message = "Please enter a valid email address (e.g., user@example.com).")
    @Size(max = 255, message = "Email must not exceed 255 characters.")
    private String email;

    @NotNull(message = "Full name cannot be empty. Please enter your full name.")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters.")
    private String fullName;

    @NotNull(message = "Google ID is required.")
    @Positive(message = "Google ID must be a valid positive number.")
    private Long googleId;

}
