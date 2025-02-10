package com.myapp.pea.DTO.Response.User;

import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Data
@SuperBuilder
public class UserResponseBaseDTO {

    private Long id;

    private String email;

    private String fullName;

    private String provider;

    private List<ListResponseDTO> lists;

    private List<TodoResponseDTO> todoList;

}
