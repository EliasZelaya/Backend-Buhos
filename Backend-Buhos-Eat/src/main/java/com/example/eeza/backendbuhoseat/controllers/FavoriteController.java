package com.example.eeza.backendbuhoseat.controllers;

import com.example.eeza.backendbuhoseat.domain.dto.FavoriteDto;
import com.example.eeza.backendbuhoseat.domain.dto.request.favorite.CreateFavoriteRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.GeneralResponse;
import com.example.eeza.backendbuhoseat.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API+FAVORITE)
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping(CREATE)
    public ResponseEntity<GeneralResponse> createFavorite(@RequestBody CreateFavoriteRequest favorite) {
        favoriteService.createFavorite(favorite);
        return GeneralResponse.builder()
                .message(ENTITY_FAVORITE+CREATED)
                .status(HttpStatus.CREATED)
                .build()
                .getResponse();
    }

    @GetMapping(GET_ONE)
    public ResponseEntity<GeneralResponse> getOne(@RequestBody FavoriteDto favoriteDto) {
        System.out.println(favoriteDto);
        favoriteService.getFavorite(favoriteDto);
        return GeneralResponse.builder()
                .message(ENTITY_FAVORITE+FOUND)
                .status(HttpStatus.OK)
                .data(true)
                .build()
                .getResponse();
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<GeneralResponse> deleteFavorite(@RequestBody FavoriteDto favoriteDto) {
        favoriteService.deleteFavorite(favoriteDto);
        return GeneralResponse.builder()
                .message(ENTITY_FAVORITE+DELETED)
                .status(HttpStatus.OK)
                .build()
                .getResponse();
    }
}
