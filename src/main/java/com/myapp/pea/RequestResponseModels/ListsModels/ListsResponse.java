package com.myapp.pea.RequestResponseModels.ListsModels;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ListsResponse {

    private Long id;

    @Size(max = 25, message = "List name must be at most 25 characters long.")
    private String listName;

}
