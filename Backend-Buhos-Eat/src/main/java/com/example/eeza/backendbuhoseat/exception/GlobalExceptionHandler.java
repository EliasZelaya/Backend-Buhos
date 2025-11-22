package com.example.eeza.backendbuhoseat.exception;

import com.example.eeza.backendbuhoseat.domain.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(LocalAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleLocalAlreadyExistException(LocalAlreadyExistException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(PlateNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlePlateNotFoundException(PlateNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(LocalNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLocalNotFoundException(LocalNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(FavoriteNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleFavoriteNotFoundException(FavoriteNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleReviewNotFoundException(ReviewNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserAlreadyHaveReviewException.class)
    public ResponseEntity<ApiErrorResponse> handleAlreadyHaveReviewException(UserAlreadyHaveReviewException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(FavoriteAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleFavoriteAlreadyExistException(FavoriteAlreadyExistException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(PlateAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handlePlateAlreadyExistException(PlateNotFoundException e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        // Obtiene los errores de validaciones de cada campo
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(BadRequestException e) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DirectoryCreateException.class)
    public ResponseEntity<ApiErrorResponse> handleDirectoryCreateException(DirectoryCreateException e) {
        return  buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(RolNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRolNotFoundException(RolNotFoundException e) {
        return  buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(JsonMapperException.class)
    public ResponseEntity<ApiErrorResponse> handleJsonMapperException(JsonMapperException e) {
        return  buildErrorResponse(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DirectionAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleDirectionAlreadyExistException(DirectionAlreadyExistException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(SubReviewAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleSubReviewAlreadyExistException(SubReviewAlreadyExistException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    //Funcion que se llama para generar una respuesta a la excepcion
    public ResponseEntity<ApiErrorResponse> buildErrorResponse(Exception e, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath(); //Obtiene la uri del error

        return ResponseEntity.status(status).body(ApiErrorResponse.builder()
                .status(status.value())
                .message(data)
                .time(LocalDate.now())
                .uri(uri)
                .build()
        );
    }
}
