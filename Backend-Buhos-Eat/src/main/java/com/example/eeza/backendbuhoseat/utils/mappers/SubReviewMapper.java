package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.CreateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.subreview.SubReviewResponse;
import com.example.eeza.backendbuhoseat.domain.entities.SubReview;

public class SubReviewMapper {
    public static SubReviewResponse toDtoResponse(SubReview subReview) {
        return SubReviewResponse.builder()
                .subReviewId(subReview.getId())
                .reviewId(subReview.getReview().getId())
                .userId(subReview.getUser().getId())
                .content(subReview.getContent())
                .build();
    }
}
