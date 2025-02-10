package com.myapp.pea.DTO.Response.User;

import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.util.stream.Collectors;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserResponseTraditionalDTO extends UserResponseBaseDTO{

    private String username;

    public static UserResponseBaseDTO fromEntity(User user) {
        return UserResponseTraditionalDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .provider(user.getProvider())
                .lists(user.getLists().stream().map(ListResponseDTO::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
