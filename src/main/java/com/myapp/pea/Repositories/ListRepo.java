package com.myapp.pea.Repositories;

import com.myapp.pea.Entities.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepo extends JpaRepository<List, Long> {
}
