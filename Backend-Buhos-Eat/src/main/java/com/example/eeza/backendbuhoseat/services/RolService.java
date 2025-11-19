package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.response.rol.RolResponse;

import java.util.UUID;

public interface RolService {
    RolResponse getRole(UUID id);
}
