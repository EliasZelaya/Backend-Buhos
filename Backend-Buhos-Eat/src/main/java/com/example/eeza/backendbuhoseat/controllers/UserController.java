package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.request.user.UpdatePasswordRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+USER)
public class UserController {
    private final UserService userService;

    @PostMapping(CREATE)
    public ResponseEntity<GeneralResponse> createUser(
            @RequestPart("createUserRequest") String createUserRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        userService.createUser(createUserRequest, image);

        return GeneralResponse.builder()
                .message(ENTITY_USER + CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<GeneralResponse> getAllUsers() {
        return GeneralResponse.builder()
                .message(ENTITY_USER+FOUND)
                .data(userService.getAllUsers())
                .build()
                .getResponse();
    }

    @GetMapping(GET_ONE+BY_EMAIL_USER)
    public ResponseEntity<GeneralResponse> getUserByEmail(@PathVariable String email) {
        return GeneralResponse.builder()
                .message(ENTITY_USER+FOUND)
                .status(HttpStatus.OK)
                .data(userService.getUserByEmail(email))
                .build()
                .getResponse();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<GeneralResponse> updateUser(
            @RequestPart("updateUserRequest") String updateRequest,
            @RequestPart(value = "image", required = false) MultipartFile newImage
            ) {
        userService.updateUser(updateRequest, newImage);
        return GeneralResponse.builder()
                .message(ENTITY_USER+UPDATED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @PutMapping(UPDATE_PASSWORD+BY_ID_USER)
    public ResponseEntity<GeneralResponse> updatePassword(
            @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
            @PathVariable UUID idUser
    ) {
        userService.updateUserPassword(idUser, updatePasswordRequest);
        return GeneralResponse.builder()
                .message(UPDATED_PASS)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }

    @DeleteMapping(DELETE+BY_ID_USER)
    public ResponseEntity<GeneralResponse> deleteUser(@PathVariable UUID idUser) {
        userService.deleteUserById(idUser);
        return GeneralResponse.builder()
                .message(ENTITY_USER+DELETED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }
}
