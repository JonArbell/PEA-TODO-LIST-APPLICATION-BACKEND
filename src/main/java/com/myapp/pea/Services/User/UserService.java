package com.myapp.pea.Services.User;

import com.myapp.pea.DTO.Request.User.UserRequestDTO;
import com.myapp.pea.DTO.Response.UserResponseDTO;
import com.myapp.pea.Entities.List;
import com.myapp.pea.Entities.User;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;
    private final ListRepo listRepo;

    public User getCurrentUser() {
        return userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public UserResponseDTO addUser(UserRequestDTO userRequest){

        var user = User.builder()
                .email(userRequest.getEmail())
                .googleId(userRequest.getGoogleId())
                .fullName(userRequest.getFullName())
                .todoList(new ArrayList<>())
                .lists(new ArrayList<>())
                .build();

        var saveUser = userRepo.save(user);

        var personalList = List.builder()
                .listName("Personal")
                .user(saveUser)
                .date(LocalDateTime.now())
                .build();

        var workList = List.builder()
                .listName("Work")
                .user(saveUser)
                .date(LocalDateTime.now())
                .build();

        var saveLists = listRepo.saveAll(java.util.List.of(personalList,workList));

        saveUser.setLists(saveLists);

        return UserResponseDTO.fromEntity(saveUser);

    }

}
