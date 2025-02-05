package com.myapp.pea.Services;

import com.myapp.pea.Model.User;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void setUser(User user){

        userRepo.save(user);

    }

}
