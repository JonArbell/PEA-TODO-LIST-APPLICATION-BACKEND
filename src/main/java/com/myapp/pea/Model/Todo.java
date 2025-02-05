package com.myapp.pea.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "TODOS")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100, message = "Task title must be between 3 and 100 characters.")
    private String title;

    @Size(max = 200, message = "Short description must not exceed 200 characters.")
    private String shortDescription;

    @NotNull(message = "Due date is required. Please provide a valid date.")
    @FutureOrPresent(message = "Due date cannot be in the past.")
    private LocalDateTime dueDate;

    @NotNull(message = "Completion status is required.")
    private Boolean done;

//    @NotNull(message = "User is required.")
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
