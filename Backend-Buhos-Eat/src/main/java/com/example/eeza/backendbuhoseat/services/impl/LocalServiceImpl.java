package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.local.CreateLocalRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.local.UpdateLocalRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.ImageInfo;
import com.example.eeza.backendbuhoseat.domain.dto.response.PageableResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.exception.*;
import com.example.eeza.backendbuhoseat.repository.LocalRepository;
import com.example.eeza.backendbuhoseat.services.LocalService;
import com.example.eeza.backendbuhoseat.services.UserService;
import com.example.eeza.backendbuhoseat.utils.handlers.ImageInfoHandler;
import com.example.eeza.backendbuhoseat.utils.handlers.SaveImages;
import com.example.eeza.backendbuhoseat.utils.handlers.Validations;
import com.example.eeza.backendbuhoseat.utils.mappers.LocalMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class LocalServiceImpl implements LocalService {
    private final LocalRepository localRepository;
    private final UserService userService;
    private final Validations validations;

    @Override
    @Transactional
    public void createLocal(String createRequest, MultipartFile image) {
        CreateLocalRequest createLocalRequest;
        try {
            createLocalRequest = new ObjectMapper().readValue(createRequest, CreateLocalRequest.class);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(JSON_MAPPER);
        }

        LocalResponse exist = LocalMapper.toDto(localRepository.findByName(createLocalRequest.getName()));

        if (exist != null)
            throw new LocalAlreadyExistException(ENTITY_LOCAL+EXISTS);

        Boolean existDirection = localRepository
                .existsLocalByDirection_LatAndDirection_Lng(
                        createLocalRequest.getDirection().getLat(),
                        createLocalRequest.getDirection().getLng()
                );

        if(existDirection)
            throw new DirectionAlreadyExistException(DIRECTION_EXIST);

        if(image != null) {
            String name = SaveImages.saveImagesHandler(image);

            createLocalRequest.setImage(name);
        }
        validations.validateRequest(createLocalRequest);
        UserResponse user = userService.getUserById(createLocalRequest.getUser());
        localRepository.save(LocalMapper.toEntityCreate(createLocalRequest, UserMapper.toEntity(user)));
    }

    public PageableResponse<LocalResponse> getAllLocals(Pageable pageable) {
        Page<Local> localPage = localRepository.findAll(pageable);

        if (localPage.isEmpty()) {
            throw new LocalNotFoundException(ENTITY_LOCAL + NOT_FOUND);
        }

        List<LocalResponse> content = localPage.map(local -> {
            LocalResponse dto = LocalMapper.toDto(local);

            if (dto.getImage() != null) {
                Path path = Paths.get(PATH_FILE + "/" + dto.getImage()).normalize();
                if (Files.exists(path)) {
                    ImageInfo imageinfo = ImageInfoHandler.getImageInfo(local.getImage());
                    dto.setImageContentType(imageinfo.getContentType());
                    dto.setImageUrl(imageinfo.getImageUrl());
                }
            }

            return dto;
        }).toList();

        return new PageableResponse<>(
                content,
                localPage.getNumber(),
                localPage.getSize(),
                localPage.getTotalElements(),
                localPage.getTotalPages(),
                localPage.isLast()
        );
    }

    @Override
    public LocalResponse getLocalById(UUID id) {
        LocalResponse local = LocalMapper.toDto(localRepository.findById(id)
                .orElseThrow(() -> new LocalNotFoundException(ENTITY_LOCAL + NOT_FOUND)));

        if(local.getImage() != null) {
            ImageInfo info = ImageInfoHandler.getImageInfo(local.getImage());
            local.setImageContentType(info.getContentType());
            local.setImageUrl(info.getImageUrl());
        }

        return local;
    }

    @Override
    @Transactional
    public void updateLocal(String updateRequest, MultipartFile image) {
        try {
            UpdateLocalRequest updateLocalRequest = new ObjectMapper().readValue(updateRequest, UpdateLocalRequest.class);

            Local local = localRepository.findById(updateLocalRequest.getId())
                    .orElseThrow(()  -> new LocalNotFoundException(ENTITY_LOCAL+NOT_FOUND));

            if(image != null && !image.isEmpty()) {
                if(local.getImage() != null) {
                    Path oldImage = Paths.get(PATH_FILE+"/"+local.getImage());
                    String newImageFile = ImageInfoHandler.replaceImage(oldImage, image);
                    local.setImage(newImageFile);
                } else {
                    String name = SaveImages.saveImagesHandler(image);
                    local.setImage(name);
                    updateLocalRequest.setImage(name);
                }
            }
            validations.validateRequest(updateLocalRequest);

            if (updateLocalRequest.getName() != null) {
                local.setName(updateLocalRequest.getName());
            }

            if (updateLocalRequest.getDescription() != null) {
                local.setDescription(updateLocalRequest.getDescription());
            }

            if (updateLocalRequest.getPhone() != null) {
                local.setPhone(updateLocalRequest.getPhone());
            }

            if (updateLocalRequest.getDirection() != null) {
                Boolean existDirection = localRepository
                        .existsLocalByDirection_LatAndDirection_LngAndIdNot(
                                updateLocalRequest.getDirection().getLat(),
                                updateLocalRequest.getDirection().getLng(),
                                updateLocalRequest.getId()
                        );

                if(existDirection) {
                    throw new DirectionAlreadyExistException(DIRECTION_EXIST);
                }

                local.setDirection(updateLocalRequest.getDirection());
            }

            if (updateLocalRequest.getType() != null) {
                local.setType(updateLocalRequest.getType());
            }

            if (updateLocalRequest.getImage() != null) {
                local.setImage(updateLocalRequest.getImage());
            }

            if (updateLocalRequest.getSchedule() != null) {
                local.setSchedule(updateLocalRequest.getSchedule());
            }

            localRepository.save(local);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(JSON_MAPPER);
        }
    }

    @Override
    @Transactional
    public void deleteLocalById(UUID id) {
        LocalResponse local = LocalMapper.toDto(localRepository.findById(id).orElseThrow(
                () -> new LocalNotFoundException(ENTITY_USER+NOT_FOUND))
        );

        try {
            Path oldImage = Paths.get(PATH_FILE+"/"+local.getImage());
            Files.deleteIfExists(oldImage);
        } catch (IOException e) {
            throw new DirectoryCreateException(e.getMessage());
        }

        localRepository.deleteById(id);
    }
}
