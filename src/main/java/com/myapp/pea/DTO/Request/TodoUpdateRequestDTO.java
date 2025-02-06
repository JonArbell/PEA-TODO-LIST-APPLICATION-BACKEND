package com.myapp.pea.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateRequestDTO extends TodoAddRequestDTO{

    private Long id;

}
