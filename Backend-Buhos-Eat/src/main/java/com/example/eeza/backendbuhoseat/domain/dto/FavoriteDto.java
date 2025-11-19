package com.example.eeza.backendbuhoseat.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FavoriteDto {
    private UUID userId;
    private UUID localId;
}
