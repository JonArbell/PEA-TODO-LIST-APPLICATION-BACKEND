package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.ListAddRequestDTO;
import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Services.ListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;

    @PostMapping("/add-list")
    public ResponseEntity<Map<String, ListResponseDTO>> testAddTodo(@Valid @RequestBody ListAddRequestDTO list){

        var newList = listService.addList(list);

        log.info("New List : {}",newList);

        return new ResponseEntity<>(Map.of("response",newList), HttpStatus.OK);
    }

    @GetMapping("/get-all-list")
    public ResponseEntity<Map<String, List<ListResponseDTO>>> getAllList(){

        return new ResponseEntity<>(Map.of("response",listService.getAllList()),HttpStatus.OK);
    }

}
