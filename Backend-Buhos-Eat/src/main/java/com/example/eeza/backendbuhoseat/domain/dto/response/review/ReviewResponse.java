package com.example.eeza.backendbuhoseat.domain.dto.response.review;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReviewResponse {
    private UUID reviewId;
    private UUID userId;
    private String content;
    private Integer stars;
}
