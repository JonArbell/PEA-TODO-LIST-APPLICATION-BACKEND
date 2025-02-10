package com.myapp.pea.DTO.Response;

import com.myapp.pea.Entities.Todo;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TodoResponseDTO {

    private Long id;

    private String title;

    private String shortDescription;

    private LocalDateTime dueDate;

    private Boolean done;

    private Long listId;

    public static TodoResponseDTO fromEntity(Todo todo){

        var listId = todo.getList() != null ? todo.getList().getId() : null;

        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .done(todo.getDone())
                .shortDescription(todo.getShortDescription())
                .listId(listId)
                .dueDate(todo.getDueDate())
                .build();
    }
}
