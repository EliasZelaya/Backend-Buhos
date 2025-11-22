package com.example.eeza.backendbuhoseat.domain.dto.request.local;

import com.example.eeza.backendbuhoseat.domain.embeddables.Direction;
import com.example.eeza.backendbuhoseat.utils.Type;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;
import static com.example.eeza.backendbuhoseat.utils.Constants.INVALID_FORMAT_IMAGE;
import static com.example.eeza.backendbuhoseat.utils.Constants.INVALID_PHONE_FORMAT;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_IMAGE;
import static com.example.eeza.backendbuhoseat.utils.Regexp.REGEXP_PHONE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocalRequest {
    @NotNull(message = ID_EMPTY)
    private UUID id;
    private String name;
    private String description;
    @Pattern(regexp = REGEXP_PHONE, message = INVALID_PHONE_FORMAT)
    private String phone;
    private Direction direction;
    private Type type;
    @Pattern(regexp = REGEXP_IMAGE,  message = INVALID_FORMAT_IMAGE)
    private String image;
    private String schedule;
}
