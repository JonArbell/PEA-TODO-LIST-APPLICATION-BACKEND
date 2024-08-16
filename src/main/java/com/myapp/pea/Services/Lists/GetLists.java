package com.myapp.pea.Services.Lists;

import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class GetLists {

    private final ListsRepo listsRepo;
    private final UserService userService;

    public List<Lists> allListsDateModified(){

        List<Lists> allLists = listsRepo.findByUserId(userService.getId());

        return allLists
                .stream()
                .sorted(Comparator.comparing(Lists::getDate))
                .toList();
    }

}
