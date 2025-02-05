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

    private Long userId;


    public static TodoResponseDTO fromEntity(Todo todo){

        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .done(todo.getDone())
                .shortDescription(todo.getShortDescription())
                .userId(todo.getUser().getId())
                .dueDate(todo.getDueDate())
                .build();
    }
}
