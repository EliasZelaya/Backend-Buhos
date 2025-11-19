package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.services.PlateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+PLATE)
public class PlateController {
    private final PlateService plateService;

    @PostMapping(CREATE)
    public ResponseEntity<GeneralResponse> createPlate(
            @RequestPart String createPlateRequest,
            @RequestPart MultipartFile image
    ) {
        plateService.createPlate(createPlateRequest, image);
        return GeneralResponse.builder()
                .message(ENTITY_PLATE+CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<GeneralResponse> getAllPlates() {
        return GeneralResponse.builder()
                .message(ENTITY_PLATE+FOUND)
                .data(plateService.getAllPlates())
                .build()
                .getResponse();
    }

    @GetMapping(GET_ONE+BY_ID_PLATE)
    public ResponseEntity<GeneralResponse> getPlateById(@PathVariable UUID idPlate) {
        return GeneralResponse.builder()
                .message(ENTITY_PLATE+FOUND)
                .data(plateService.getPlateById(idPlate))
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL+BY_ID_LOCAL)
    public ResponseEntity<GeneralResponse> getPlateByLocal(@PathVariable UUID idLocal) {
        return GeneralResponse.builder()
                .message(ENTITY_PLATE+FOUND)
                .data(plateService.getPlatesByLocal(idLocal))
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<GeneralResponse> updatePlateById(
            @RequestPart String updatePlateRequest,
            @RequestPart(required = false) MultipartFile image
    ) {
        plateService.updatePlate(updatePlateRequest, image);
        return GeneralResponse.builder()
                .message(ENTITY_PLATE+UPDATED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @DeleteMapping(DELETE+BY_ID_PLATE)
    public ResponseEntity<GeneralResponse> deletePlate(@PathVariable UUID idPlate) {
        plateService.deletePlate(idPlate);
        return GeneralResponse.builder()
                .message(ENTITY_PLATE+DELETED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }
}
