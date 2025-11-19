package com.example.eeza.backendbuhoseat.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import static com.example.eeza.backendbuhoseat.utils.Constants.INVALID_EMAIL;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_PASSWORD;

@Data
@Builder
public class LoginRequest {
    @Email(message = INVALID_EMAIL)
    private String email;
    @Pattern(regexp = REGEXP_PASSWORD)
    private String password;
}
