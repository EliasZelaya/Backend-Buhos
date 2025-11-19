package com.example.eeza.backendbuhoseat.domain.dto.response.local;

import com.example.eeza.backendbuhoseat.utils.Type;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LocalResponse {
    private UUID id;
    private String name;
    private String description;
    private String phone;
    private String direction;
    private String type;
    private String schedule;
    private String image;
    private String imageContentType;
    private String imageUrl;
    private UUID idUser;
}
