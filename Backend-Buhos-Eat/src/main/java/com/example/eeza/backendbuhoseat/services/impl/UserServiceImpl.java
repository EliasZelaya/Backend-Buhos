package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.user.CreateUserRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.user.UpdatePasswordRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.user.UpdateUserRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.ImageInfo;
import com.example.eeza.backendbuhoseat.domain.dto.response.rol.RolResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import com.example.eeza.backendbuhoseat.domain.entities.User;
import com.example.eeza.backendbuhoseat.exception.*;
import com.example.eeza.backendbuhoseat.repository.UserRepository;
import com.example.eeza.backendbuhoseat.services.RolService;
import com.example.eeza.backendbuhoseat.services.UserService;
import com.example.eeza.backendbuhoseat.utils.handlers.ImageInfoHandler;
import com.example.eeza.backendbuhoseat.utils.handlers.SaveImages;
import com.example.eeza.backendbuhoseat.utils.mappers.RolMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.UserMapper;
import com.example.eeza.backendbuhoseat.utils.handlers.Validations;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RolService rolService;
    private final Validations validations;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void createUser(String createRequest, MultipartFile image) {
        CreateUserRequest createUserRequest;
        try {
            createUserRequest = new ObjectMapper().readValue(createRequest, CreateUserRequest.class);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(JSON_MAPPER);
        }

        UserResponse exist = UserMapper.toDto(userRepository.findByEmail(createUserRequest.getEmail()));

        if (exist != null)
            throw new UserAlreadyExistException(ENTITY_USER+EXISTS);

        if(image != null) {
            String name = SaveImages.saveImagesHandler(image);

            createUserRequest.setImage(name);
        }
        validations.validateRequest(createUserRequest);
        RolResponse rol = rolService.getRole(createUserRequest.getRol());
        createUserRequest.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        userRepository.save(UserMapper.toEntityCreate(createUserRequest, RolMapper.toEntity(rol)));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = UserMapper.toDtoList(userRepository.findAll());
        if(users.isEmpty())
            throw new UserNotFoundException(ENTITY_USER+NOT_FOUND);

        for (UserResponse user : users) {
            if (user.getImage() != null) {
                Path path = Paths.get(PATH_FILE + "/" + user.getImage()).normalize();
                if (Files.exists(path)) {
                    ImageInfo imageinfo = ImageInfoHandler.getImageInfo(user.getImage());
                    user.setImageContentType(imageinfo.getContentType());
                    user.setImageUrl(imageinfo.getImageUrl());
                }
            }
        }

        return users;
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        UserResponse user = UserMapper.toDto(userRepository.findByEmail(email));
        if(user == null)
            throw new UserNotFoundException(ENTITY_USER+NOT_FOUND);

        if(user.getImage() != null) {
            ImageInfo info = ImageInfoHandler.getImageInfo(user.getImage());
            user.setImageContentType(info.getContentType());
            user.setImageUrl(info.getImageUrl());
        }

        return user;
    }

    @Override
    public UserResponse getUserById(UUID id) {
        return UserMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ENTITY_USER+NOT_FOUND)));
    }

    @Override
    @Transactional
    public void updateUser(String updateRequest, MultipartFile newImage) {
        try {
            UpdateUserRequest updateUserRequest = new ObjectMapper().readValue(updateRequest, UpdateUserRequest.class);

            User user = userRepository.findById(updateUserRequest.getId())
                            .orElseThrow(()  -> new UserNotFoundException(ENTITY_USER+NOT_FOUND));

            if(!updateUserRequest.getUsername().isEmpty()) {
                user.setUsername(updateUserRequest.getUsername());
            }

            if(updateUserRequest.getBanned() != null) {
                if(!updateUserRequest.getBanned().equals(!user.getBanned())) {
                    user.setBanned(updateUserRequest.getBanned());
                }
            }

            if(newImage != null && !newImage.isEmpty()) {
                if(user.getImage() != null) {
                    Path oldImage = Paths.get(PATH_FILE+"/"+user.getImage());
                    String newImageFile = ImageInfoHandler.replaceImage(oldImage, newImage);
                    user.setImage(newImageFile);
                } else {
                    String name = SaveImages.saveImagesHandler(newImage);
                    user.setImage(name);
                }
            }
            userRepository.save(user);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(JSON_MAPPER);
        }
    }

    @Override
    @Transactional
    public void updateUserPassword(UUID id, UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(()  -> new UserNotFoundException(ENTITY_USER+NOT_FOUND));

        if(!updatePasswordRequest.getPassword().equals(updatePasswordRequest.getConfirmPassword()))
            throw new BadRequestException(NOT_PASSWORD_EQUALS);

        user.setPassword(updatePasswordRequest.getPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        UserResponse user = UserMapper.toDto(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ENTITY_USER+NOT_FOUND))
        );

        try {
            Path oldImage = Paths.get(PATH_FILE+"/"+user.getImage());
            Files.deleteIfExists(oldImage);
        } catch (IOException e) {
            throw new DirectoryCreateException(e.getMessage());
        }

        userRepository.deleteById(id);
    }
}
