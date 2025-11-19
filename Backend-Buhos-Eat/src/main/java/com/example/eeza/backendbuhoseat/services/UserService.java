package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.request.user.UpdatePasswordRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(String createUserRequest, MultipartFile image);
    List<UserResponse> getAllUsers();
    UserResponse getUserByEmail(String email);
    UserResponse getUserById(UUID id);
    void updateUser(String updateUserRequest, MultipartFile newImage);
    void updateUserPassword(UUID id, UpdatePasswordRequest updatePasswordRequest);
    void deleteUserById(UUID id);
}
