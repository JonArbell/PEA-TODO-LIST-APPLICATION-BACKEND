package com.myapp.pea.Services;

import com.myapp.pea.DTO.Request.UserRequestDTO;
import com.myapp.pea.DTO.Response.UserResponseDTO;
import com.myapp.pea.Entities.User;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserResponseDTO setUser(UserRequestDTO userRequest){

        var user = User.builder()
                .email(userRequest.getEmail())
                .googleId(userRequest.getGoogleId())
                .fullName(userRequest.getFullName())
                .build();

        var saveUser = userRepo.save(user);

        return UserResponseDTO.fromEntity(saveUser);

    }

}
