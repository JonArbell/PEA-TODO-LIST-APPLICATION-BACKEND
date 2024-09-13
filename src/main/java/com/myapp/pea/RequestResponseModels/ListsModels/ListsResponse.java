package com.myapp.pea.RequestResponseModels.ListsModels;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ListsResponse {

    private Long id;

    private String listName;

}
