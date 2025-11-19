package com.example.eeza.backendbuhoseat.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse {
    private String message;
    private Object data;

    @Builder.Default
    @JsonIgnore
    private HttpStatus status = HttpStatus.OK;

    @JsonIgnore
    public ResponseEntity<GeneralResponse> getResponse() {
        return ResponseEntity.status(status).body(this);
    }
}
