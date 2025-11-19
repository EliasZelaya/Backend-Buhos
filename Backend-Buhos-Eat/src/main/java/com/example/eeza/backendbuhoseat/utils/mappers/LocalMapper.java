package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.request.local.CreateLocalRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.local.UpdateLocalRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.User;
import com.example.eeza.backendbuhoseat.utils.Type;

import java.util.List;

public class LocalMapper {
    public static LocalResponse toDto(Local local) {
        if (local == null)
            return null;

        return LocalResponse.builder()
                .id(local.getId())
                .name(local.getName())
                .description(local.getDescription())
                .phone(local.getPhone())
                .direction(local.getDirection())
                .image(local.getImage())
                .schedule(local.getSchedule())
                .idUser(local.getUser().getId())
                .type(local.getType().name())
                .build();
    }

    public static Local toEntity(LocalResponse localResponse) {
        return Local.builder()
                .name(localResponse.getName())
                .description(localResponse.getDescription())
                .phone(localResponse.getPhone())
                .direction(localResponse.getDirection())
                .type(Type.valueOf(localResponse.getType().toUpperCase()))
                .schedule(localResponse.getSchedule())
                .image(localResponse.getImage())
                .build();
    }

    public static Local toEntityCreate(CreateLocalRequest createLocalRequest, User user) {
        return Local.builder()
                .name(createLocalRequest.getName())
                .description(createLocalRequest.getDescription())
                .phone(createLocalRequest.getPhone())
                .direction(createLocalRequest.getDirection())
                .type(createLocalRequest.getType())
                .image(createLocalRequest.getImage())
                .schedule(createLocalRequest.getSchedule())
                .user(user)
                .build();
    }

    public static Local toEntityUpdate(UpdateLocalRequest updateLocalRequest) {
        return Local.builder()
                .name(updateLocalRequest.getName())
                .description(updateLocalRequest.getDescription())
                .phone(updateLocalRequest.getPhone())
                .direction(updateLocalRequest.getDirection())
                .type(updateLocalRequest.getType())
                .image(updateLocalRequest.getImage())
                .schedule(updateLocalRequest.getSchedule())
                .build();
    }

    public  static List<LocalResponse> toDtoList(List<Local> locals) {
        return  locals.stream()
                .map(LocalMapper::toDto)
                .toList();
    }
}
