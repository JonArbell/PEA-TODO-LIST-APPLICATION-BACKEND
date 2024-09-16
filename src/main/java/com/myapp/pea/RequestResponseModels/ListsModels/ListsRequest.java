package com.myapp.pea.RequestResponseModels.ListsModels;


import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
public class ListsRequest {

    private Long id;

    @Size(max = 25, message = "List name must be at most 25 characters long.")
    private String listName;

}
