package com.myapp.pea.Repositories;

import com.myapp.pea.Entities.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ListRepo extends JpaRepository<List, Long> {

    Optional<List> findByUser_IdAndId(Long user_Id, Long id);
    java.util.List<List> findAllByUser_Id(Long user_Id);
}
