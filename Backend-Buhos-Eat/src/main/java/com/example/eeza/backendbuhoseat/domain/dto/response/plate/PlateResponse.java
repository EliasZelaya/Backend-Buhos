package com.example.eeza.backendbuhoseat.domain.dto.response.plate;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PlateResponse {
    private UUID id;
    private String name;
    private String image;
    private String imageUrl;
    private Double price;
}
