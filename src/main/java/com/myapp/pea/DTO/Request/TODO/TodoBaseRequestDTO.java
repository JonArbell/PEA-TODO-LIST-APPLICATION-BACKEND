package com.myapp.pea.DTO.Request.TODO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TodoBaseRequestDTO {

    @Size(min = 3, max = 100, message = "Task title must be between 3 and 100 characters.")
    private String title;

    @Size(max = 200, message = "Short description must not exceed 200 characters.")
    private String shortDescription;

    @NotNull(message = "Due date is required. Please provide a valid date.")
    @FutureOrPresent(message = "Due date cannot be in the past.")
    private LocalDateTime dueDate;

    @NotNull(message = "Completion status is required.")
    private Boolean done;

}
