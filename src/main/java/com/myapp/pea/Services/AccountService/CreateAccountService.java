package com.myapp.pea.Services.AccountService;

import com.myapp.pea.Entities.Lists;
import com.myapp.pea.Entities.User;
import com.myapp.pea.Exceptions.PasswordConfirmationMismatchException;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Repository.UsersRepo;
import com.myapp.pea.RequestResponseModels.UserModels.CreateAccountRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAccountService {

    private final UsersRepo usersRepo;
    private final ListsRepo listsRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean checkUsername(String username) throws Exception{

        User usn = usersRepo.findByUsername(username);

        boolean isValidUsn = usn == null;

        if(!isValidUsn)
            throw new Exception("The username "+username+" is already used. Please try another username.");

        return true;
    }

    public boolean checkEmail(String email) throws Exception{

        User mail = usersRepo
                            .findByEmail(email);

        boolean isValidEmail = mail == null;

        if(!isValidEmail)
            throw new Exception("The email "+email+" is already used. Please try another email.");

        return true;
    }

    public boolean checkPassword(String password,String confirmPassword) throws PasswordConfirmationMismatchException {

        if(!password.equals(confirmPassword)){
            throw new PasswordConfirmationMismatchException("Passwords do not match. Please try again.");
        }
        return true;
    }

    public void createAccount(CreateAccountRequest createAccountRequest) throws Exception{

        try {

            if(checkEmail(createAccountRequest.getEmail())){

                if(checkUsername(createAccountRequest.getUsername())){

                    if(checkPassword(createAccountRequest.getPassword(),createAccountRequest.getConfirmPassword())){
                        createAccountRequest.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));

                        var saveUser = User
                                .builder()
                                .firstName(createAccountRequest.getFirstName())
                                .lastName(createAccountRequest.getLastName())
                                .email(createAccountRequest.getEmail())
                                .username(createAccountRequest.getUsername())
                                .password(createAccountRequest.getPassword())
                                .build();

                        usersRepo.save(saveUser);

                        User searchUser = usersRepo.findByUsername(createAccountRequest.getUsername());

                        Lists personal = Lists
                                .builder()
                                .userId(searchUser.getId())
                                .date(LocalDateTime.now())
                                .listName("Personal")
                                .build();

                        Lists work = Lists
                                .builder()
                                .date(LocalDateTime.now().plusSeconds(3))
                                .userId(searchUser.getId())
                                .listName("Work")
                                .build();

                        listsRepo.save(personal);
                        listsRepo.save(work);

                    }
                }
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
