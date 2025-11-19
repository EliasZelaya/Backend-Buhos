package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.request.review.CreateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.review.ReviewResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.Review;
import com.example.eeza.backendbuhoseat.domain.entities.User;

import java.util.List;

public class ReviewMapper {
    public static ReviewResponse toDto(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .content(review.getContent())
                .stars(review.getStars())
                .build();
    }

    public static Review toEntityCreate(CreateReviewRequest createReviewRequest, User user, Local local) {
        return Review.builder()
                .user(user)
                .local(local)
                .content(createReviewRequest.getContent())
                .stars(createReviewRequest.getStars())
                .build();
    }

    public static List<ReviewResponse> toDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewMapper::toDto)
                .toList();
    }
}
