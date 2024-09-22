package com.kaique.admin_store.services;

import com.kaique.admin_store.dtos.UserDto;
import com.kaique.admin_store.exceptions.UserAlreadyExistsException;
import com.kaique.admin_store.repositories.UserRepository;
import com.kaique.admin_store.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserDto register(UserDto userDto) {
        if (userRepository.findFirstByEmail(userDto.email()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        var user = userDto.toEntity();
        user.setPassword(passwordEncoder.encode(userDto.password()));
        var newUser = userRepository.save(user);

        String token = tokenService.generateToken(newUser);
        return UserDto.fromEntity(newUser);
    }

    public UserDto login(UserDto userDto) {
        var existentUser = userRepository.findFirstByEmail(userDto.email()).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));

        if (!passwordEncoder.matches(userDto.password(), existentUser.getPassword())) {
            throw new AccessDeniedException("Invalid credentials");
        }

        String token = tokenService.generateToken(existentUser);
        return UserDto.fromEntity(existentUser);
    }
}
