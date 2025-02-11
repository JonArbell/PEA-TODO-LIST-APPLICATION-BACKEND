package com.myapp.pea.DTO.Request.User.Login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestLoginDTO {

    @NotNull(message = "Email is required.")
    @Email(message = "Please enter a valid email address (e.g., user@example.com).")
    @Size(max = 255, message = "Email must not exceed 255 characters.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character."
    )
    private String password;

}
