package com.myapp.pea.DTO.Request.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserRequestGoogleBaseDTO extends UserRequestBaseDTO {

    @NotNull(message = "Google ID is required.")
    @Pattern(regexp = "^[0-9]+$", message = "Google ID must contain only digits.")
    @Size(min = 21, max = 21, message = "Google ID must be exactly 21 digits.")
    private String googleId;

}
