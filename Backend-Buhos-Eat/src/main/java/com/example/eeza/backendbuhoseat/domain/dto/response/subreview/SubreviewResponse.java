package com.example.eeza.backendbuhoseat.domain.dto.response.subreview;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SubreviewResponse {
    private UUID subreviewId;
    private UUID reviewId;
    private UUID userId;
    private String content;

}
