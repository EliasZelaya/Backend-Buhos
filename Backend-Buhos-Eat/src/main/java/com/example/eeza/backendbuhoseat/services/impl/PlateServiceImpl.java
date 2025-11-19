package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.plate.CreatePlateRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.plate.UpdatePlateRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.user.UpdateUserRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.ImageInfo;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.plate.PlateResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.Plate;
import com.example.eeza.backendbuhoseat.domain.entities.User;
import com.example.eeza.backendbuhoseat.exception.*;
import com.example.eeza.backendbuhoseat.repository.LocalRepository;
import com.example.eeza.backendbuhoseat.repository.PlateRepository;
import com.example.eeza.backendbuhoseat.services.PlateService;
import com.example.eeza.backendbuhoseat.utils.handlers.ImageInfoHandler;
import com.example.eeza.backendbuhoseat.utils.handlers.SaveImages;
import com.example.eeza.backendbuhoseat.utils.handlers.Validations;
import com.example.eeza.backendbuhoseat.utils.mappers.LocalMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.PlateMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
public class PlateServiceImpl implements PlateService {
    private final PlateRepository plateRepository;
    private final Validations validations;
    private final LocalRepository localRepository;

    @Override
    @Transactional
    public void createPlate(String createRequest, MultipartFile image) {
        CreatePlateRequest createPlateRequest;
        try {
            createPlateRequest = new ObjectMapper().readValue(createRequest, CreatePlateRequest.class);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(JSON_MAPPER);
        }
        Plate exist = plateRepository.findByName(createPlateRequest.getName());

        if(exist != null) {
            throw new PlateAlreadyExistException(ENTITY_PLATE+EXISTS);
        }

        if(image != null) {
            String name = SaveImages.saveImagesHandler(image);

            createPlateRequest.setImage(name);
        }
        validations.validateRequest(createPlateRequest);
        Local local = localRepository.findById(createPlateRequest.getLocal()).orElseThrow();
        plateRepository.save(PlateMapper.toEntityCreate(createPlateRequest, local));
    }

    @Override
    public List<PlateResponse> getAllPlates() {
        List<PlateResponse> plates = PlateMapper.toDtoList(plateRepository.findAll());
        if(plates.isEmpty())
            throw new PlateNotFoundException(ENTITY_PLATE+NOT_FOUND);

        for (PlateResponse plate : plates) {
            if (plate.getImage() != null) {
                Path path = Paths.get(PATH_FILE + "/" + plate.getImage()).normalize();
                if (Files.exists(path)) {
                    ImageInfo imageinfo = ImageInfoHandler.getImageInfo(plate.getImage());
                    plate.setImageUrl(imageinfo.getImageUrl());
                }
            }
        }

        return plates;
    }

    @Override
    public PlateResponse getPlateById(UUID id) {
        PlateResponse plate = PlateMapper.toDto(plateRepository.findById(id)
                .orElseThrow(() -> new  PlateNotFoundException(ENTITY_PLATE+NOT_FOUND)));

        if(plate.getImage() != null) {
            ImageInfo info = ImageInfoHandler.getImageInfo(plate.getImage());
            plate.setImageUrl(info.getImageUrl());
        }

        return plate;
    }

    @Override
    public List<PlateResponse> getPlatesByLocal(UUID local) {
        localRepository.findById(local)
                .orElseThrow(() -> new LocalNotFoundException(ENTITY_LOCAL+NOT_FOUND));

        List<PlateResponse> plates = PlateMapper.toDtoList(
                plateRepository.getPlatesByLocalId(local)
        );

        if(plates.isEmpty())
            throw new PlateNotFoundException(ENTITY_PLATE+NOT_FOUND);

        for (PlateResponse plate : plates) {
            if (plate.getImage() != null) {
                Path path = Paths.get(PATH_FILE + "/" + plate.getImage()).normalize();
                if (Files.exists(path)) {
                    ImageInfo imageinfo = ImageInfoHandler.getImageInfo(plate.getImage());
                    plate.setImageUrl(imageinfo.getImageUrl());
                }
            }
        }

        return plates;
    }

    @Override
    @Transactional
    public void updatePlate(String updateRequest, MultipartFile newImage) {
        try {
            UpdatePlateRequest updatePlateRequest = new ObjectMapper().readValue(updateRequest, UpdatePlateRequest.class);

            Plate plate = plateRepository.findById(updatePlateRequest.getIdPlate())
                    .orElseThrow(()  -> new UserNotFoundException(ENTITY_PLATE+NOT_FOUND));

            if(updatePlateRequest.getName() != null && !updatePlateRequest.getName().isEmpty()) {
                plate.setName(updatePlateRequest.getName());
            }

            if(updatePlateRequest.getPrice() != null) {
                plate.setPrice(updatePlateRequest.getPrice());
            }

            if(newImage != null && !newImage.isEmpty()) {
                if(plate.getImage() != null) {
                    Path oldImage = Paths.get(PATH_FILE+"/"+plate.getImage());
                    String newImageFile = ImageInfoHandler.replaceImage(oldImage, newImage);
                    plate.setImage(newImageFile);
                } else {
                    String name = SaveImages.saveImagesHandler(newImage);
                    plate.setImage(name);
                }
            }
            plateRepository.save(plate);
        } catch (JsonProcessingException e) {
            throw new JsonMapperException(JSON_MAPPER);
        }
    }

    @Override
    @Transactional
    public void deletePlate(UUID id) {
        PlateResponse plate = PlateMapper.toDto(plateRepository.findById(id).orElseThrow(
                () -> new PlateNotFoundException(ENTITY_PLATE+NOT_FOUND))
        );

        try {
            Path oldImage = Paths.get(PATH_FILE+"/"+plate.getImage());
            Files.deleteIfExists(oldImage);
        } catch (IOException e) {
            throw new DirectoryCreateException(e.getMessage());
        }

        plateRepository.deleteById(id);
    }
}
