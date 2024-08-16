package com.myapp.pea.Services;

import com.myapp.pea.Exceptions.PasswordTooShortException;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.User;
import com.myapp.pea.Repository.UsersRepo;
import com.myapp.pea.Services.Lists.ListsOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAccountService {

    private final UsersRepo usersRepo;
    private final ListsOperation listsOperation;
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

    public boolean checkPass(String password) throws PasswordTooShortException {

        if(password.length() < 8){
            throw new PasswordTooShortException("Password must be at least 8 characters long.");
        }

        return true;
    }

    public void createAccount(User user) throws Exception{

        try {

            if(checkEmail(user.getEmail())){

                if(checkUsername(user.getUsername())){

                    if(checkPass(user.getPassword())){
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        usersRepo.save(user);

                        User searchUser = usersRepo.findByUsername(user.getUsername());

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

                        listsOperation.createNewList(personal);
                        listsOperation.createNewList(work);

                    }
                }
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
