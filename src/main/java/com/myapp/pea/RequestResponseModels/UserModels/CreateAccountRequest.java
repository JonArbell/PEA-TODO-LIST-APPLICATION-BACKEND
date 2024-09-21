package com.myapp.pea.RequestResponseModels.UserModels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class CreateAccountRequest {

    @Size(min = 2, max = 25, message = "Username must be between 2 and 25 characters long.")
    private String username;

    @Size(max = 35, message = "Firstname must be at most 35 characters long.")
    private String firstName;

    @Size(max = 35, message = "Lastname must be at most 35 characters long.")
    private String lastName;

    @Email(message = "Please provide a valid email address.")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String confirmPassword;

}
