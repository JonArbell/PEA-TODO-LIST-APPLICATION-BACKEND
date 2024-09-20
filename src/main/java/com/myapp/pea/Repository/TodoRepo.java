package com.myapp.pea.Repository;

import com.myapp.pea.Entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TodoRepo extends JpaRepository<Todo,Long> {

    List<Todo> findByUserId(Long userId);
    Optional<List<Todo>> findByUserIdAndTitleContainingIgnoreCase(Long userId, String title);
}
