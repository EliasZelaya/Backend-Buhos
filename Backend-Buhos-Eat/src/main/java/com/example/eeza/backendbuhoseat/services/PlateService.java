package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.response.plate.PlateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface PlateService {
    void createPlate(String createRequest, MultipartFile image);
    List<PlateResponse> getAllPlates();
    PlateResponse getPlateById(UUID id);
    List<PlateResponse> getPlatesByLocal(UUID local);
    void updatePlate(String updateRequest, MultipartFile image);
    void deletePlate(UUID id);
}
