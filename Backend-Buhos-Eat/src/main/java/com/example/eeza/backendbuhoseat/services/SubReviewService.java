package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.CreateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.UpdateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.subreview.SubReviewResponse;

import java.util.List;
import java.util.UUID;

public interface SubReviewService {
    void createSubReview(CreateSubReviewRequest createSubReviewRequest, UUID localId);
    List<SubReviewResponse> getSubReviewsByReview(UUID id);
    List<SubReviewResponse> getSubReviewsByUserId(UUID id);
    void updateSubReview(UUID id, UpdateSubReviewRequest updateSubReviewRequest);
    void deleteSubReview(UUID id);
}
