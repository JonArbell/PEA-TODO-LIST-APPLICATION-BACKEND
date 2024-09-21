package com.myapp.pea.RestControllers.Authenticated.crudLists;

import com.myapp.pea.RequestResponseModels.ListsModels.ListsRequest;
import com.myapp.pea.RequestResponseModels.MessageResponse;
import com.myapp.pea.Services.ListsService.ListsOperationService;
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
    private final ListsOperationService listsOperationService;

    @PostMapping("/list/add")
    public ResponseEntity<?> addList(@Valid @RequestBody ListsRequest listsRequest, BindingResult bindingResult){

        logger.info("List : {}",listsRequest);

        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error ->{
                errors.put("addListError",error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

        }else{
            listsOperationService.createNewList(listsRequest);
            return new ResponseEntity<>(new MessageResponse("List added successfully!"),HttpStatus.CREATED);
        }

    }

    @PatchMapping("/list/edit")
    public ResponseEntity<?> editListName (@Valid @RequestBody ListsRequest listsRequest,BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("List name : {}",listsRequest);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put("editListNameError",error.getDefaultMessage());
            });
        }

        try{
            listsOperationService.updateListName(listsRequest);
            return new ResponseEntity<>(new MessageResponse("List renamed successfully!"),HttpStatus.OK);
        } catch (Exception e){
            errors.put("editListNameError",e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/list/delete/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id, @RequestBody boolean deleteTasks){

        Map<String, String> errors = new HashMap<>();

        logger.info("Delete all tasks ? : {}",deleteTasks);
        logger.info("Delete List Id : {}",id);

        try{

            listsOperationService.deleteList(id,deleteTasks);
            return new ResponseEntity<>(new MessageResponse("List deleted successfully!"),HttpStatus.OK);
        } catch (Exception e){
            errors.put("deleteListError",e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}
