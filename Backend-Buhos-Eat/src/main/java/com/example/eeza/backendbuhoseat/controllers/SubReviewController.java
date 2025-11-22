package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.CreateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.UpdateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.domain.entities.SubReview;
import com.example.eeza.backendbuhoseat.services.SubReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+SUBREVIEW)
public class SubReviewController {
    private final SubReviewService subReviewService;

    @PostMapping(CREATE+BY_ID_LOCAL)
    public ResponseEntity<GeneralResponse> createSubReview(@Valid @RequestBody CreateSubReviewRequest createSubReviewRequest, @PathVariable UUID idLocal){
        subReviewService.createSubReview(createSubReviewRequest, idLocal);
        return GeneralResponse.builder()
                .message(ENTITY_SUBREVIEW+CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<GeneralResponse> getAllSubReviewsByReview(@RequestParam UUID idReview){
        return GeneralResponse.builder()
                .message(ENTITY_SUBREVIEW+FOUND)
                .status(HttpStatus.OK)
                .data(subReviewService.getSubReviewsByReview(idReview))
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL+BY_ID_USER)
    public ResponseEntity<GeneralResponse> getAllSubReviewsByUser(@PathVariable UUID idUser){
        return GeneralResponse.builder()
                .message(ENTITY_SUBREVIEW+FOUND)
                .status(HttpStatus.OK)
                .data(subReviewService.getSubReviewsByUserId(idUser))
                .build()
                .getResponse();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<GeneralResponse> updateSubReview(
            @Valid @RequestBody UpdateSubReviewRequest updateSubReviewRequest
    ){
        subReviewService.updateSubReview(updateSubReviewRequest);
        return GeneralResponse.builder()
                .message(ENTITY_SUBREVIEW+UPDATED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }
}
