package com.myapp.pea.RestControllers.Authenticated.crudLists;

import com.myapp.pea.Services.ListsService.GetLists;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CrudLists {

    private final GetLists getLists;

    @GetMapping("")
    public ResponseEntity<?> getAllListsByDateModified(){

        return new ResponseEntity<>(getLists.allListsDateModified(),HttpStatus.OK);
    }

}
