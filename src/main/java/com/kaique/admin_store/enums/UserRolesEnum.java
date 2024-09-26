package com.kaique.admin_store.enums;

import lombok.Getter;

@Getter
public enum UserRolesEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    UserRolesEnum(String role) {
        this.role = role;
    }
}
