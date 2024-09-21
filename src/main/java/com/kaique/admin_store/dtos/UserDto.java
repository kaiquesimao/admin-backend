package com.kaique.admin_store.dtos;

import com.kaique.admin_store.models.User;

import java.io.Serializable;
import java.util.Optional;

/**
 * DTO for {@link com.kaique.admin_store.models.User}
 */
public record UserDto(Optional<Long> id, String name, String tel, String email, String password,
                      Optional<String> status,
                      Optional<String> role) implements Serializable {

    public static UserDto fromEntity(User user) {
        return new UserDto(Optional.ofNullable(user.getId()), user.getName(), user.getTel(), user.getEmail(), user.getPassword(),
                Optional.ofNullable(user.getStatus()), Optional.ofNullable(user.getRole()));
    }

    public User toEntity() {
        return new User(id.orElse(null), name, tel, email, password, status.orElse(null), role.orElse(null));
    }
}