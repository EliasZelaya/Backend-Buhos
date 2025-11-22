package com.example.eeza.backendbuhoseat.domain.dto.request.local;

import com.example.eeza.backendbuhoseat.domain.embeddables.Direction;
import com.example.eeza.backendbuhoseat.utils.Type;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_IMAGE;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_PHONE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocalRequest {
    @NotBlank(message = EMPTY_LOCAL_NAME)
    private String name;

    @NotBlank(message = EMPTY_LOCAL_DESCRIPTION)
    private String description;

    @NotBlank(message = EMPTY_LOCAL_PHONE)
    @Pattern(regexp = REGEXP_PHONE, message = INVALID_PHONE_FORMAT)
    private String phone;

    @NotNull(message = EMPTY_LOCAL_DIRECTION)
    @Valid
    private Direction direction;

    private Type type;

    @Pattern(regexp = REGEXP_IMAGE,  message = INVALID_FORMAT_IMAGE)
    @NotBlank(message = EMPTY_IMAGE)
    private String image;

    @NotBlank(message = EMPTY_LOCAL_SCHEDULE)
    private String schedule;

    private UUID user;
}
