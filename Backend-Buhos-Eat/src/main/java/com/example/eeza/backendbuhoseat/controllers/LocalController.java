package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.PageableResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import com.example.eeza.backendbuhoseat.services.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+LOCAL)
public class LocalController {
    private final LocalService localService;

    @PostMapping(CREATE)
    public ResponseEntity<GeneralResponse> createLocal(
            @RequestPart String createLocalRequest,
            @RequestPart(required = false) MultipartFile image
    ) {
        localService.createLocal(createLocalRequest, image);
        return GeneralResponse.builder()
                .message(ENTITY_LOCAL+CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<GeneralResponse> getAllLocals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        //Evalua de que manera lo ordenara
        Sort sort = sortOrder.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        PageableResponse<LocalResponse> pagedResponse = localService.getAllLocals(pageable);
        return GeneralResponse.builder()
                .message(ENTITY_LOCAL+FOUND)
                .data(pagedResponse)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ONE+BY_ID_LOCAL)
    public ResponseEntity<GeneralResponse> getLocalById(@PathVariable UUID idLocal) {

        return GeneralResponse.builder()
                .message(ENTITY_LOCAL+FOUND)
                .data(localService.getLocalById(idLocal))
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<GeneralResponse> updateLocalByIdl(
            @RequestPart String localUpdateRequest,
            @RequestPart(required = false) MultipartFile image
    ) {
        localService.updateLocal(localUpdateRequest, image);
        return GeneralResponse.builder()
                .message(ENTITY_LOCAL+UPDATED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @DeleteMapping(DELETE+BY_ID_LOCAL)
    public ResponseEntity<GeneralResponse> deleteLocalById(@PathVariable UUID idLocal) {
        localService.deleteLocalById(idLocal);
        return GeneralResponse.builder()
                .message(ENTITY_LOCAL+DELETED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }
}
