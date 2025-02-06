package com.myapp.pea.DTO.Response;

import com.myapp.pea.Entities.User;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UserResponseDTO {

    private Long id;

    private String email;

    private String fullName;

    private Long googleId;

    private List<ListResponseDTO> lists;

    private List<TodoResponseDTO> todoList;

    public static UserResponseDTO fromEntity(User user){

        var convertListToResponse = user.getLists().stream().map(ListResponseDTO::fromEntity).toList();

        var covertTodoToResponse = user.getTodoList().stream().map(TodoResponseDTO::fromEntity).toList();

        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .lists(convertListToResponse)
                .todoList(covertTodoToResponse)
                .googleId(user.getGoogleId())
                .build();
    }
}
