package com.myapp.pea.RequestResponseModels.WebPagesResponse;

import com.myapp.pea.RequestResponseModels.ListsModels.ListsResponse;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class HomeResponse {

    private List<TodoResponse> todoResponse;
    private List<ListsResponse> listsResponse;

}
