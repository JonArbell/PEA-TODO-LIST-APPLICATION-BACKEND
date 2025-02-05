package com.myapp.pea.Repositories;

import com.myapp.pea.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    void setUser(User user);
    Optional<User> getUser(User user);

}
