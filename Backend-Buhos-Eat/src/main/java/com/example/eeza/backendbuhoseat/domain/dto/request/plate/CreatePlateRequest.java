package com.example.eeza.backendbuhoseat.domain.dto.request.plate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_IMAGE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlateRequest {
    @NotBlank(message = EMPTY_PLATE_NAME)
    private String name;

    @Pattern(regexp = REGEXP_IMAGE,  message = INVALID_FORMAT_IMAGE)
    @NotBlank(message = EMPTY_IMAGE)
    private String image;

    @Positive(message = PRICE_INVALID)
    @NotNull(message = PRICE_EMPTY)
    private Double price;

    @NotNull(message = LOCAL_EMPTY)
    private UUID local;
}
