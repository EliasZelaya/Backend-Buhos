package com.example.eeza.backendbuhoseat.utils.handlers;

import com.example.eeza.backendbuhoseat.domain.dto.request.user.CreateUserRequest;
import com.example.eeza.backendbuhoseat.exception.BadRequestException;
import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validations {
    private final Validator validator;

    public <T> void validateRequest(T request) {
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String errorMsg = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new BadRequestException(errorMsg);
        }
    }
}
