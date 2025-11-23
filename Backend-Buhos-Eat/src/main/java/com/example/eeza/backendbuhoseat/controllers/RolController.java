package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.request.role.CreateRolRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.services.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+ROLE)
public class RolController {
    private final RolService rolService;

    @PostMapping(CREATE)
    public ResponseEntity<GeneralResponse> createRolController(@Valid @RequestBody CreateRolRequest createRolRequest) {
        rolService.createRol(createRolRequest);
        return GeneralResponse.builder()
                .message(ENTITY_ROLE+CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }
}
