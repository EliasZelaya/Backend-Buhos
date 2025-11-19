package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.request.LoginRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.TokenResponse;
import com.example.eeza.backendbuhoseat.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        TokenResponse.builder().accessToken(token).build();
        return ResponseEntity
                .ok(
                        TokenResponse.builder()
                        .accessToken(token)
                        .build()
                );
    }
}
