package com.myapp.pea.RequestModels.TodoModels;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class TodoRequest {

    @Size(max = 35, message = "Title must be at most 35 characters long.")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done;

    @Column(nullable = false)
    private String formattedDate;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime dateModified;

    @Size(max = 200, message = "Description must be at most 200 characters long.")
    private String shortDescription;

}
