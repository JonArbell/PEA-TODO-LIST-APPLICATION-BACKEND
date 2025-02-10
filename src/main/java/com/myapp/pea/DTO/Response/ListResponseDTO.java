package com.myapp.pea.DTO.Response;

import com.myapp.pea.Entities.List;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ListResponseDTO {

    private Long id;

    private String listName;

    private LocalDateTime date;

    public static ListResponseDTO fromEntity(List list){

        return ListResponseDTO.builder()
                .id(list.getId())
                .date(list.getDate())
                .listName(list.getListName())
                .build();
    }
}
