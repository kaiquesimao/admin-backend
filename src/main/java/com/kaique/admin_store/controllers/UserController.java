package com.kaique.admin_store.controllers;

import com.kaique.admin_store.dtos.UserDto;
import com.kaique.admin_store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository UserRepository;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {

        return ResponseEntity.ok().body(UserRepository.findAll().stream().map(UserDto::new).toList());
    }
}
