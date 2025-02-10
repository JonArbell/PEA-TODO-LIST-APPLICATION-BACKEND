package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.List.ListAddRequestDTO;
import com.myapp.pea.DTO.Request.List.ListUpdateRequestDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Services.List.ListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;

    @PostMapping("/add-list")
    public ResponseEntity<Map<String, ListResponseDTO>> addList(@Valid @RequestBody ListAddRequestDTO list){

        var newList = listService.addList(list);

        log.info("New List : {}", newList);

        return new ResponseEntity<>(Map.of("added-list", newList), HttpStatus.CREATED);
    }

    @PatchMapping("/update-list")
    public ResponseEntity<Map<String, ListResponseDTO>> updateList(@Valid @RequestBody ListUpdateRequestDTO update){

        var updated = listService.updateList(update);

        log.info("Updated List : {}", updated);

        return new ResponseEntity<>(Map.of("updated-list", updated), HttpStatus.OK);
    }

    @DeleteMapping("/delete-list/{id}")
    public ResponseEntity<Map<String, ListResponseDTO>> deleteListById(@PathVariable Long id){

        var updated = listService.deleteListItem(id);

        log.info("Deleted List item : {}", updated);

        return new ResponseEntity<>(Map.of("deleted-list-by-id", updated), HttpStatus.OK);
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<Map<String, Integer>> deleteAllTodo(@RequestParam  boolean isDeleteAll){

        var deleted = listService.deleteAllList(isDeleteAll);

        log.info("Deleted Lists: {}", deleted);

        return new ResponseEntity<>(Map.of("deleted-lists", deleted),HttpStatus.OK);
    }

    @GetMapping("/get-list/{id}")
    public ResponseEntity<Map<String, ListResponseDTO>> getListById(@PathVariable Long id){

        var list = listService.getListItem(id);

        log.info("Get List item : {}", list);

        return new ResponseEntity<>(Map.of("get-list-by-id", list), HttpStatus.OK);
    }

    @GetMapping("/get-list")
    public ResponseEntity<Map<String, List<ListResponseDTO>>> getAllList(){

        var allList = listService.getAllList();

        log.info("Get all list: {}", allList);

        return new ResponseEntity<>(Map.of("get-list-by-id", allList), HttpStatus.OK);
    }

}
