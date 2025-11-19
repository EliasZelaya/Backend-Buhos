package com.example.eeza.backendbuhoseat.domain.dto.request.favorite;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.ID_EMPTY;

@Data
@Builder
public class CreateFavoriteRequest {
    @NotNull(message = ID_EMPTY)
    private UUID userId;
    @NotNull(message = ID_EMPTY)
    private UUID localId;
}
