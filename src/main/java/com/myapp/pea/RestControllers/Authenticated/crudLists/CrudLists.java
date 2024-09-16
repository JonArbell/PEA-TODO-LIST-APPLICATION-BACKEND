package com.myapp.pea.RestControllers.Authenticated.crudLists;

import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.RequestResponseModels.ListsModels.ListsRequest;
import com.myapp.pea.RequestResponseModels.MessageResponse;
import com.myapp.pea.Services.ListsService.ListsOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authenticated")
public class CrudLists {

    private final Logger logger = LoggerFactory.getLogger(CrudLists.class);
    private final ListsOperation listsOperation;

    @PostMapping("/list/add")
    public ResponseEntity<?> addList(@Valid @RequestBody ListsRequest listsRequest, BindingResult bindingResult){

        logger.info("List : {}",listsRequest);

        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error ->{
                errors.put(error.getField(),error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

        }else{
            listsOperation.createNewList(listsRequest);
            return new ResponseEntity<>(new MessageResponse("Successfully Add list"),HttpStatus.CREATED);
        }

    }

    @PatchMapping("/list/edit")
    public ResponseEntity<?> editListName (@Valid @RequestBody ListsRequest listsRequest,BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("List name : {}",listsRequest);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(),error.getDefaultMessage());
            });
        }

        try{
            listsOperation.updateListName(listsRequest);
            return new ResponseEntity<>(new MessageResponse("Edit list name successfully"),HttpStatus.OK);
        }catch(TodoListNotFoundException e){
            errors.put(TodoListNotFoundException.class.getSimpleName(),e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
    }

}
