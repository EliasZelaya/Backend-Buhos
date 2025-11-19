package com.example.eeza.backendbuhoseat.services;

import com.example.eeza.backendbuhoseat.domain.dto.request.favorite.CreateFavoriteRequest;
import com.example.eeza.backendbuhoseat.domain.dto.FavoriteDto;

public interface FavoriteService {
    void createFavorite(CreateFavoriteRequest createFavoriteRequest);
    void getFavorite(FavoriteDto favoriteId);
    void deleteFavorite(FavoriteDto favorite);
}
