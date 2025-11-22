package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.request.review.CreateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.review.UpdateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.review.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    void createReview(CreateReviewRequest createReviewRequest);
    ReviewResponse findById(UUID id);
    List<ReviewResponse> findByLocalId(UUID localId);
    void updateReview(UpdateReviewRequest updateReviewRequest);
    void deleteReview(UUID id);
}
