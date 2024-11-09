package com.myapp.pea.RestControllers.Authenticated.WebPagesRequests.Nav;

import com.myapp.pea.RequestResponseModels.ListsModels.ListsResponse;
import com.myapp.pea.RequestResponseModels.UserModels.UserResponse;
import com.myapp.pea.RequestResponseModels.WebPagesResponse.PagesResponse;
import com.myapp.pea.Services.AccountService.UserService;
import com.myapp.pea.Services.ListsService.GetLists;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/authenticated")
public class NavPages {

    private final UserService userService;
    private final GetLists getLists;

    private UserResponse userResponse(){
        return UserResponse.builder()
                .id(userService.getId())
                .username(userService.getUsername())
                .email(userService.getEmail())
                .firstName(userService.getFirstName())
                .lastName(userService.getLastName())
                .build();
    }

    private List<ListsResponse> getAllListsResponse(){

        var listsOfLists = new ArrayList<ListsResponse>();

        getLists.allListsDateModified().forEach(data -> {
            var lists = ListsResponse.builder()
                    .listName(data.getListName())
                    .id(data.getId())
                    .build();

            listsOfLists.add(lists);
        });

        return listsOfLists;
    }

    @GetMapping("/about-us")
    public ResponseEntity<?> aboutUs(){

        return new ResponseEntity<>(new PagesResponse(null,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }

    @GetMapping("/contact-us")
    public ResponseEntity<?> contactUs(){
        return new ResponseEntity<>(new PagesResponse(null,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }

    @GetMapping("/settings")
    public ResponseEntity<?> settings(){
        return new ResponseEntity<>(new PagesResponse(null,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }

}
