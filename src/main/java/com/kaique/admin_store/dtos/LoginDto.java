package com.kaique.admin_store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for login
 */
public record LoginDto(@NotNull @Email @Size(max = 100) String email,
                       @NotNull @Size(min = 6, max = 20) String password) implements Serializable {
}