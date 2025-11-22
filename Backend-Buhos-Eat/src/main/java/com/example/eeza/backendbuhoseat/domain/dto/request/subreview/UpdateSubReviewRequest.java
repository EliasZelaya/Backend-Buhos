package com.example.eeza.backendbuhoseat.domain.dto.request.subreview;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@Data
@Builder
public class UpdateSubReviewRequest {
    @NotNull(message = ID_EMPTY)
    private UUID idSubReview;
    private String content;
    @NotNull(message = ID_EMPTY)
    private UUID idReview;
}
