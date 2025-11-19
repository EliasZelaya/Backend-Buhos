package com.example.eeza.backendbuhoseat.domain.dto.request.plate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlateRequest {
    private UUID idPlate;
    private String name;
    private String image;
    private Double price;
}
