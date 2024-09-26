package com.kaique.admin_store.dtos;

import com.kaique.admin_store.enums.UserRolesEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Collection;

/**
 * DTO for register
 */
public record RegisterDto(@NotNull @Size(max = 100) String name, @Nullable @Size(max = 20) String tel,
                          @NotNull @Email @Size(max = 100) String email,
                          @NotNull @Size(min = 6, max = 20) String password,
                          @NotNull Collection<UserRolesEnum> roles) implements Serializable {
}