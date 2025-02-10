package com.myapp.pea.DTO.Response.User;

import com.myapp.pea.DTO.Response.ListResponseDTO;
import com.myapp.pea.Entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class UserResponseGoogleBaseDTO extends UserResponseBaseDTO{

    private String googleId;

    public static UserResponseBaseDTO fromEntity(User user) {
        return UserResponseGoogleBaseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .lists(user.getLists().stream().map(ListResponseDTO::fromEntity).collect(Collectors.toList()))
                .googleId(user.getGoogleId())
                .provider(user.getProvider())
                .build();
    }
}
