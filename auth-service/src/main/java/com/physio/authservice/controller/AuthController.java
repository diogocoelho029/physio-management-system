package com.physio.authservice.controller;

import com.physio.authservice.dto.AuthResponse;
import com.physio.authservice.dto.LoginRequest;
import com.physio.authservice.dto.LoginResponse;
import com.physio.authservice.dto.RegisterRequest;
import com.physio.authservice.model.User;
import com.physio.authservice.security.JwtUtil;
import com.physio.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(user.getUsername(), "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        User user = userService.getByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(new LoginResponse(user.getUsername(), token));
    }
}
