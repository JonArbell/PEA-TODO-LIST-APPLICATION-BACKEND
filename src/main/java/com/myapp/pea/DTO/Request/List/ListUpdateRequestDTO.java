package com.myapp.pea.DTO.Request.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListUpdateRequestDTO extends ListBaseRequestDTO{

    private Long id;

}
