package com.example.eeza.backendbuhoseat.domain.dto.request.user;

import com.example.eeza.backendbuhoseat.domain.entities.Rol;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;
import static com.example.eeza.backendbuhoseat.utils.Regexp.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = EMPTY_USER_NAME)
    private String username;

    @NotBlank(message = EMPTY_EMAIL)
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotBlank(message = EMPTY_PASSWORD)
    @Pattern(regexp = REGEXP_PASSWORD, message = NOT_ALPHANUMERIC_USER_PASSWORD)
    @Size(min = 6, message = NOT_LONG_PASSWORD)
    private String password;

    @Pattern(regexp = REGEXP_IMAGE,  message = INVALID_FORMAT_IMAGE)
    private String image;

    @NotNull(message = NULL_USER_ROL)
    private UUID rol;
}
