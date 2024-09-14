package com.myapp.pea.RestControllers.Authenticated.crudLists;

import com.myapp.pea.RequestResponseModels.ListsModels.ListsRequest;
import com.myapp.pea.RequestResponseModels.MessageResponse;
import com.myapp.pea.RestControllers.Authenticated.crudTodo.CrudTodo;
import com.myapp.pea.Services.ListsService.ListsOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
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

}
