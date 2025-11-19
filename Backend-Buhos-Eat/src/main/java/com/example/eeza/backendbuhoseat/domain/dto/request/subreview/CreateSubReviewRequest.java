package com.example.eeza.backendbuhoseat.domain.dto.request.subreview;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.EMPTY_CONTENT;
import static com.example.eeza.backendbuhoseat.utils.Constants.ID_EMPTY;

@Data
@Builder
public class CreateSubReviewRequest {
    @NotBlank(message = EMPTY_CONTENT)
    private String content;
    @NotNull(message = ID_EMPTY)
    private UUID idUser;
    @NotNull(message = ID_EMPTY)
    private UUID idReview;
}
