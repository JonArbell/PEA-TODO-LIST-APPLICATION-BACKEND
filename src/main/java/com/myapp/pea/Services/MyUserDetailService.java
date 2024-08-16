package com.myapp.pea.Services;

import com.myapp.pea.Models.User;
import com.myapp.pea.Models.UserPrincipal;
import com.myapp.pea.Repository.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = usersRepo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("This user is not registered yet.");
        }

        return new UserPrincipal(user);
    }
}
