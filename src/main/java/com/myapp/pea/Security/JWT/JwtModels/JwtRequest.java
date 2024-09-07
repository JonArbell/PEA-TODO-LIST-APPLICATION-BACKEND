package com.myapp.pea.Security.JWT.JwtModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRequest {

    @Size(min = 2, max = 25, message = "Username must be between 2 and 25 characters long.")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @NotBlank(message = "Password is mandatory")
    private String password;

}
