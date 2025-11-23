package com.example.eeza.backendbuhoseat.domain.dto.request.role;

import com.example.eeza.backendbuhoseat.utils.Rol;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import static com.example.eeza.backendbuhoseat.utils.Constants.ROL_NAME_EMPTY;

@Data
@Builder
public class CreateRolRequest {
    @NotNull(message = ROL_NAME_EMPTY)
    private Rol rolName;
}
