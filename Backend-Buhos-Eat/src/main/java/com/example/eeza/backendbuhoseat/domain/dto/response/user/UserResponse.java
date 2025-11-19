package com.example.eeza.backendbuhoseat.domain.dto.response.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String username;
    private String image;
    private String imageContentType;
    private String imageUrl;
    private UUID idRol;
}
