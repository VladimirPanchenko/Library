package ru.itprogram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itprogram.domain.dto.User;
import ru.itprogram.service.impl.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.allUsers());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registration(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}
