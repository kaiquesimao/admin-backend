package com.kaique.admin_store.services;

import com.kaique.admin_store.dtos.UserDto;
import com.kaique.admin_store.exceptions.UserAlreadyExistsException;
import com.kaique.admin_store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto signUp(UserDto userDto) {
        var user = userDto.toEntity();
        var alreadyExists = userRepository.findFirstByEmail(user.getEmail());

        if (alreadyExists.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        return UserDto.fromEntity(userRepository.save(user));
    }
}
