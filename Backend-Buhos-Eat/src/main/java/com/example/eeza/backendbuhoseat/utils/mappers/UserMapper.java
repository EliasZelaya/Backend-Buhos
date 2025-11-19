package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.request.user.CreateUserRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.user.UpdateUserRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Rol;
import com.example.eeza.backendbuhoseat.domain.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toEntityCreate(CreateUserRequest createUserRequest, Rol rol) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .image(createUserRequest.getImage())
                .banned(false)
                .rol(rol)
                .build();
    }

    public static User toEntity(UserResponse userResponse) {
        return User.builder()
                .id(userResponse.getId())
                .username(userResponse.getUsername())
                .image(userResponse.getImage())
                .build();
    }

    public static UserResponse toDto(User user) {
        if (user == null)
            return null;

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .image(user.getImage())
                .idRol(user.getRol().getId())
                .build();
    }

    public static List<UserResponse> toDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }
}
