package com.myapp.pea.Services.ListsService;

import com.myapp.pea.Entities.Lists;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
