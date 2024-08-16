package com.myapp.pea.Services;


import com.myapp.pea.Models.User;
import com.myapp.pea.Models.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    public boolean isUserAuthenticated(){
        return getUserPrincipal().isPresent();
    }

    private Optional<UserPrincipal> getUserPrincipal(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if(authentication.getPrincipal() instanceof UserPrincipal){
            return Optional.of((UserPrincipal) authentication.getPrincipal());
        }

        return Optional.empty();
    }


    public String getUsername() {
        return getUserPrincipal().map(UserPrincipal::getUsername).orElse("anonymousUser");
    }

    public String getEmail(){
        return getUserPrincipal().map(UserPrincipal::getEmail).orElse("anonymousUser");
    }

    public Long getId(){
        return getUserPrincipal().map(UserPrincipal::getId).orElse(0L);
    }

    public String getFirstName() {
        return getUserPrincipal().map(UserPrincipal::getFirstName).orElse("anonymousUser");
    }
    public String getLastName() {
        return getUserPrincipal().map(UserPrincipal::getLastName).orElse("anonymousUser");
    }
}
