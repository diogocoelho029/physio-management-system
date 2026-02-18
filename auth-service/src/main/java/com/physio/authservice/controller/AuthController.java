package com.physio.authservice.controller;

import com.physio.authservice.dto.AuthResponse;
import com.physio.authservice.dto.RegisterRequest;
import com.physio.authservice.model.User;
import com.physio.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(user.getUsername(), "User registered successfully"));
    }
}
