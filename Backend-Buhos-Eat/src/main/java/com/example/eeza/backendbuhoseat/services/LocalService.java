package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.response.PageableResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface LocalService {
    void createLocal(String createRequest, MultipartFile image);
    PageableResponse<LocalResponse> getAllLocals(Pageable pageable);
    LocalResponse getLocalById(UUID id);
    void updateLocal(String updateRequest, MultipartFile image);
    void deleteLocalById(UUID id);
}
