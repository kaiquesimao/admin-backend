package com.kaique.admin_store.controllers;

import com.kaique.admin_store.dtos.LoginDto;
import com.kaique.admin_store.dtos.RegisterDto;
import com.kaique.admin_store.dtos.UserDto;
import com.kaique.admin_store.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginDto loginDto) {
        var login = authService.login(loginDto);
        return ResponseEntity.ok().body(login);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterDto registerDto) {
        var user = authService.register(registerDto);
        return ResponseEntity.ok().body(user);
    }
}
