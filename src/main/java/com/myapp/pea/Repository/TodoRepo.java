package com.myapp.pea.Repository;

import com.myapp.pea.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepo extends JpaRepository<Todo,Long> {

    List<Todo> findByUserId(Long userId);

}
