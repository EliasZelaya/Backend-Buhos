package com.example.eeza.backendbuhoseat.utils.mappers;

import com.example.eeza.backendbuhoseat.domain.dto.FavoriteDto;
import com.example.eeza.backendbuhoseat.domain.entities.Favorite;

public class FavoriteMapper {
    public static FavoriteDto toDto(Favorite favorite) {
        if (favorite == null)
            return null;

        return FavoriteDto.builder()
                .userId(favorite.getUser().getId())
                .localId(favorite.getLocal().getId())
                .build();
    }
}
