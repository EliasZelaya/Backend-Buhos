package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.request.role.CreateRolRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.rol.RolResponse;

import java.util.UUID;

public interface RolService {
    void createRol(CreateRolRequest createRolRequest);
    RolResponse getRole(UUID id);
}
