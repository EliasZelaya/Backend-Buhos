package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.response.rol.RolResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Rol;

public class RolMapper {
    public static RolResponse toDto(Rol rol) {
        return RolResponse.builder()
                .id(rol.getId())
                .build();
    }

    public static Rol toEntity(RolResponse rol) {
        return Rol.builder()
                .id(rol.getId())
                .build();
    }
}
