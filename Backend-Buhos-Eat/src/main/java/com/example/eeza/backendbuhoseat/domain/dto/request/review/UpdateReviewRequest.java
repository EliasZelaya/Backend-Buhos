package com.example.eeza.backendbuhoseat.domain.dto.request.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@Data
@Builder
public class UpdateReviewRequest {
    @NotNull(message = ID_EMPTY)
    private UUID reviewId;
    private String content;
    @Positive(message = LESS_ZERO_STARS)
    @Min(0)
    @Max(value = 5, message = MORE_FIVE_STARS)
    private Integer stars;
}
