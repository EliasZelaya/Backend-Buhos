package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.request.plate.CreatePlateRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.plate.PlateResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.Plate;

import java.util.List;

public class PlateMapper {
    public static PlateResponse toDto(Plate plate) {
        return PlateResponse.builder()
                .id(plate.getId())
                .name(plate.getName())
                .image(plate.getImage())
                .price(plate.getPrice())
                .build();
    }

    public static Plate toEntityCreate(CreatePlateRequest createPlateRequest, Local local) {
        return Plate.builder()
                .name(createPlateRequest.getName())
                .price(createPlateRequest.getPrice())
                .image(createPlateRequest.getImage())
                .local(local)
                .build();
    }

    public  static List<PlateResponse> toDtoList(List<Plate> plates) {
        return  plates.stream()
                .map(PlateMapper::toDto)
                .toList();
    }
}
