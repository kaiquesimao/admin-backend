package com.kaique.admin_store.dtos;

import com.kaique.admin_store.enums.UserRolesEnum;
import com.kaique.admin_store.models.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

/**
 * DTO for {@link com.kaique.admin_store.models.User}
 */
public record UserDto(@NotNull UUID id, @NotNull @Size(max = 100) String name, @Nullable @Size(max = 20) String tel,
                      @NotNull @Size(max = 100) String email,
                      @Nullable String status, @NotNull Collection<UserRolesEnum> role,
                      @Nullable String token) implements Serializable {

    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getTel(), user.getEmail(), user.getStatus(), user.getRoles(), null);
    }

    public UserDto(User user, String token) {
        this(user.getId(), user.getName(), user.getTel(), user.getEmail(), user.getStatus(), user.getRoles(), token);
    }
}