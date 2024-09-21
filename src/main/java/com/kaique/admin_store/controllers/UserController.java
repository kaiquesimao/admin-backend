package com.kaique.admin_store.controllers;

import com.kaique.admin_store.dtos.UserDto;
import com.kaique.admin_store.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
        var user = userService.signUp(userDto);

        return ResponseEntity.ok().body(user);
    }
}
