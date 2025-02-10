package com.myapp.pea.Repositories;

import com.myapp.pea.Entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Long> {

    Optional<Todo> findByUser_IdAndId(Long user_Id, Long id);
    List<Todo> findAllByUser_Id(Long user_Id);
    int deleteAllByUser_Id(Long user_Id);
}
