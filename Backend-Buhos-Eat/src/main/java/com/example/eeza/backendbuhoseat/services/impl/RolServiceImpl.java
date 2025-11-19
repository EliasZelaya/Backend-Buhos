package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.response.rol.RolResponse;
import com.example.eeza.backendbuhoseat.exception.RolNotFoundException;
import com.example.eeza.backendbuhoseat.repository.RolRepository;
import com.example.eeza.backendbuhoseat.services.RolService;
import com.example.eeza.backendbuhoseat.utils.mappers.RolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public RolResponse getRole(UUID id) {
        return RolMapper.toDto(rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException("Rol not found"))
        );
    }
}
