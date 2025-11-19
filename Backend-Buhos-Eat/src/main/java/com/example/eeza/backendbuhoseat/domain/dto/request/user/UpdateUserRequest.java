package com.example.eeza.backendbuhoseat.domain.dto.request.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.INVALID_FORMAT_IMAGE;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_IMAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private UUID id;
    private String username;
    @Pattern(regexp = REGEXP_IMAGE,  message = INVALID_FORMAT_IMAGE)
    private String image;
    private Boolean banned;
}
