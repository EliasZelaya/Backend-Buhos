package com.example.eeza.backendbuhoseat.domain.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.example.eeza.backendbuhoseat.utils.Constants.EMPTY_PASSWORD;
import static com.example.eeza.backendbuhoseat.utils.Constants.NOT_LONG_PASSWORD;

@Data
@Builder
public class UpdatePasswordRequest {
    @NotBlank(message = EMPTY_PASSWORD)
    @Size(min = 6, message = NOT_LONG_PASSWORD)
    private String password;

    @NotBlank(message = EMPTY_PASSWORD)
    @Size(min = 6, message = NOT_LONG_PASSWORD)
    private String confirmPassword;
}
