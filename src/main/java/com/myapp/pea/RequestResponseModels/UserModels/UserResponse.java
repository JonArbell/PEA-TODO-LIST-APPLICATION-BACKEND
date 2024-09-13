package com.myapp.pea.RequestResponseModels.UserModels;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponse {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

}
