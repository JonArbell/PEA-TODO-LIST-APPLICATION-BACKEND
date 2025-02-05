package com.myapp.pea.DTO.Response;

import com.myapp.pea.Entities.Todo;
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

    private List<Todo> todoList;

    public static UserResponseDTO fromEntity(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .googleId(user.getGoogleId())
                .todoList(user.getTodoList())
                .build();
    }
}
