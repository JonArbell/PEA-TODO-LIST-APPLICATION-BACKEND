package com.myapp.pea.Repository;

import com.myapp.pea.Models.Lists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ListsRepo extends JpaRepository<Lists,Long> {

    List<Lists> findByUserId(Long userId);

}
