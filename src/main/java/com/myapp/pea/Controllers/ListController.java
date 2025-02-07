package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.List.ListAddRequestDTO;
import com.myapp.pea.DTO.Request.List.ListUpdateRequestDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Services.ListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

        log.info("New List : {}",newList);

        return new ResponseEntity<>(Map.of("added-list",newList), HttpStatus.CREATED);
    }

    @PatchMapping("/update-list")
    public ResponseEntity<Map<String, ListResponseDTO>> updateList(@Valid @RequestBody ListUpdateRequestDTO update){

        var updated = listService.updateList(update);

        log.info("Updated List : {}",updated);

        return new ResponseEntity<>(Map.of("updated-list",updated), HttpStatus.OK);
    }

    @DeleteMapping("/delete-list/{id}")
    public ResponseEntity<Map<String, ListResponseDTO>> deleteListById(@PathVariable Long id){

        var updated = listService.deleteListById(id);

        log.info("Deleted List item : {}",updated);

        return new ResponseEntity<>(Map.of("deleted-list-by-id",updated), HttpStatus.OK);
    }

    @GetMapping("/get-list/{id}")
    public ResponseEntity<Map<String, ListResponseDTO>> getListById(@PathVariable Long id){

        var get = listService.getListById(id);

        log.info("Get List item : {}",get);

        return new ResponseEntity<>(Map.of("get-list-by-id",get), HttpStatus.OK);
    }

}
