package com.myapp.pea.DTO.Request.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ListBaseRequestDTO {

    @NotNull(message = "List name is required. Please provide a valid list name.")
    @Size(max = 25, message = "List name must be at most 25 characters long.")
    private String listName;


}
