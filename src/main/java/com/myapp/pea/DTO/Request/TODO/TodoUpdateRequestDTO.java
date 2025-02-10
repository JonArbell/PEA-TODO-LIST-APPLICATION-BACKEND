package com.myapp.pea.DTO.Request.TODO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TodoUpdateRequestDTO extends TodoBaseRequestDTO {

    private Long id;

    private Long listId;

}
