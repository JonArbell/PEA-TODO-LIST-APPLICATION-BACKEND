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

    private Long userId;

    public static ListResponseDTO fromEntity(List list){

        return ListResponseDTO.builder()
                .id(list.getId())
                .listName(list.getListName())
                .userId(list.getUser().getId())
                .date(list.getDate())
                .build();
    }
}
