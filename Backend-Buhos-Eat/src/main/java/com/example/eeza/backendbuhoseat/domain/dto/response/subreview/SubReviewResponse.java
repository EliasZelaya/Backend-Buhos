package com.example.eeza.backendbuhoseat.domain.dto.response.subreview;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SubReviewResponse {
    private UUID subReviewId;
    private UUID reviewId;
    private UUID userId;
    private String content;
}
