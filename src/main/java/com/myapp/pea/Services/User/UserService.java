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
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.EmailAlreadyExistsException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.GoogleIdAlreadyExistsException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public User getCurrentUser() {

        var email = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("Email : {}",email);

        if(email != null)
            return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        throw new UserNotFoundException("User is not authenticated.");
    }

    public UserResponseBaseDTO addUserTraditional(UserRequestTraditionalDTO userRequest){

        if(!isEmailNotRegistered(userRequest.getEmail()))
            throw new EmailAlreadyExistsException("This email is already used.");

        var user = User.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
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

    public boolean isEmailNotRegistered(String email){
        return userRepo.findByEmail(email)
                .isEmpty();
    }

    private boolean isGoogleIdAlreadyRegistered(String id){
        return userRepo.findByGoogleId(id)
                .isPresent();
    }

    public UserResponseBaseDTO addUserGoogleOath(UserRequestGoogleBaseDTO userRequest){

        if(isGoogleIdAlreadyRegistered(userRequest.getGoogleId()))
            throw new GoogleIdAlreadyExistsException("This google id : ("+ userRequest.getGoogleId() +") is already " +
                    "exists.");

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
