package com.myapp.pea.DTO.Request.User;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestBaseDTO {

    @NotNull(message = "Email is required.")
    @Email(message = "Please enter a valid email address (e.g., user@example.com).")
    @Size(max = 255, message = "Email must not exceed 255 characters.")
    private String email;

    @NotNull(message = "Full name cannot be empty. Please enter your full name.")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters.")
    private String fullName;

}
