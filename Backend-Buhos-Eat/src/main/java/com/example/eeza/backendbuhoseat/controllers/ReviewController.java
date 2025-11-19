package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.request.review.CreateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.review.UpdateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Review;
import com.example.eeza.backendbuhoseat.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+REVIEW)
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(CREATE)
    public ResponseEntity<GeneralResponse> createReview(@Valid @RequestBody CreateReviewRequest createReviewRequest) {
        reviewService.createReview(createReviewRequest);
        return GeneralResponse.builder()
                .message(ENTITY_REVIEW+CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL+BY_ID_LOCAL)
    public ResponseEntity<GeneralResponse> getAllLocals(@PathVariable UUID idLocal) {
        return GeneralResponse.builder()
                .message(ENTITY_REVIEW+FOUND)
                .status(HttpStatus.OK)
                .data(reviewService.findByLocalId(idLocal))
                .build()
                .getResponse();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<GeneralResponse> updateReview(@Valid @RequestBody UpdateReviewRequest updateReviewRequest) {
        reviewService.updateReview(updateReviewRequest);
        return GeneralResponse.builder()
                .message(ENTITY_REVIEW+UPDATED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @DeleteMapping(DELETE+BY_ID_REVIEW)
    public ResponseEntity<GeneralResponse> deleteReview(@PathVariable UUID idReview) {
        reviewService.deleteReview(idReview);
        return GeneralResponse.builder()
                .message(ENTITY_REVIEW+DELETED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }
}
