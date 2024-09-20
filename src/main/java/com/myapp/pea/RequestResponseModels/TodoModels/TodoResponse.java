package com.myapp.pea.RequestResponseModels.TodoModels;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Setter
public class TodoResponse {

    private Long id;

    private String title;

    private boolean done;

    private String formattedDate;

    private LocalDate date;

    private LocalDateTime dateModified;

    private String shortDescription;

    private Long listId;
    private String listName;
}
