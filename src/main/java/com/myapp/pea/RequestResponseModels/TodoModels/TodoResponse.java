package com.myapp.pea.RequestResponseModels.TodoModels;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class TodoResponse {

    private Long id;

    @Size(max = 35, message = "Title must be at most 35 characters long.")

    private String title;

    private boolean done;

    private String formattedDate;

    private LocalDate date;

    private LocalDateTime dateModified;

    @Size(max = 200, message = "Description must be at most 200 characters long.")
    private String shortDescription;

    @Size(max = 25, message = "List name must be at most 25 characters long.")
    private String listName;
}
