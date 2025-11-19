package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
