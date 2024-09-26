package com.kaique.admin_store.exceptions;

import com.kaique.admin_store.models.User;

/**
 * Exception for when a {@link User} already exists
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}