package com.myapp.pea.Services.User;

import com.myapp.pea.DTO.Request.User.UserRequestGoogleBaseDTO;
import com.myapp.pea.DTO.Request.User.UserRequestTraditionalDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.DTO.Response.User.UserResponseBaseDTO;
import com.myapp.pea.DTO.Response.User.UserResponseGoogleBaseDTO;
import com.myapp.pea.DTO.Response.User.UserResponseTraditionalDTO;
import com.myapp.pea.Entities.List;
import com.myapp.pea.Entities.User;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.TodoRepo;
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
    private final TodoRepo todoRepo;

    public User getCurrentUser() {
        return userRepo.findById(1L)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public UserResponseBaseDTO addUserTraditional(UserRequestTraditionalDTO userRequest){

        var user = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .fullName(userRequest.getFullName())
                .provider("LOCAL")
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

        return UserResponseTraditionalDTO.fromEntity(saveUser);

    }

    public java.util.List<? extends UserResponseBaseDTO> getAllUsers(){

        return userRepo.findAll().stream().map(user ->{

            var allLists = listRepo.findAllByUser_Id(user.getId()).stream().map(ListResponseDTO::fromEntity).toList();
            var allTodos = todoRepo.findAllByUser_Id(user.getId()).stream().map(TodoResponseDTO::fromEntity).toList();

            return UserResponseBaseDTO.builder()
                    .email(user.getEmail())
                    .lists(allLists)
                    .provider(user.getProvider())
                    .todoList(allTodos)
                    .fullName(user.getFullName())
                    .id(user.getId())
                    .build();

        }).toList();
    }

    public boolean findByEmail(String id){
        return userRepo.findByEmail(id)
                .isEmpty();
    }

    public UserResponseBaseDTO addUserGoogleOath(UserRequestGoogleBaseDTO userRequest){

        var user = User.builder()
                .googleId(userRequest.getGoogleId())
                .email(userRequest.getEmail())
                .fullName(userRequest.getFullName())
                .provider("GOOGLE")
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

        return UserResponseGoogleBaseDTO.fromEntity(saveUser);

    }

}
