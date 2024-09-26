package com.kaique.admin_store.services;

import com.kaique.admin_store.dtos.LoginDto;
import com.kaique.admin_store.dtos.RegisterDto;
import com.kaique.admin_store.dtos.UserDto;
import com.kaique.admin_store.exceptions.UserAlreadyExistsException;
import com.kaique.admin_store.models.User;
import com.kaique.admin_store.repositories.UserRepository;
import com.kaique.admin_store.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserDto register(RegisterDto registerDto) {
        if (userRepository.findFirstByEmail(registerDto.email()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        var user = new User(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.password()));

        var newUser = userRepository.save(user);
        String token = tokenService.generateToken(newUser);

        return new UserDto(user, token);
    }

    public Authentication authenticate(LoginDto loginDto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());

        try {
            return authenticationManager.authenticate(userNamePassword);
        } catch (AuthenticationException e) {
            throw new AccessDeniedException("Invalid credentials");
        }
    }

    public UserDto login(LoginDto loginDto) {
        Authentication auth = authenticate(loginDto);
        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return new UserDto(user, token);
    }
}
