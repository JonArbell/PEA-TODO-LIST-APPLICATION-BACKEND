package com.myapp.pea.Repository;

import com.myapp.pea.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<User,Long>{

    User findByUsername(String username);
    User findByEmail(String email);
}
