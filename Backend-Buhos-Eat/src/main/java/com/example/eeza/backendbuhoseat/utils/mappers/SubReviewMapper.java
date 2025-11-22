package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.CreateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.subreview.SubReviewResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Review;
import com.example.eeza.backendbuhoseat.domain.entities.SubReview;
import com.example.eeza.backendbuhoseat.domain.entities.User;

public class SubReviewMapper {
    public static SubReviewResponse toDtoResponse(SubReview subReview) {
        return SubReviewResponse.builder()
                .subReviewId(subReview.getId())
                .reviewId(subReview.getReview().getId())
                .userId(subReview.getUser().getId())
                .content(subReview.getContent())
                .build();
    }

    public static SubReview toEntityCreate(CreateSubReviewRequest createSubReviewRequest, Review review, User user) {
        return SubReview.builder()
                .review(review)
                .user(user)
                .content(createSubReviewRequest.getContent())
                .build();
    }
}
