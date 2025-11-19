package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.favorite.CreateFavoriteRequest;
import com.example.eeza.backendbuhoseat.domain.dto.FavoriteDto;
import com.example.eeza.backendbuhoseat.domain.entities.Favorite;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.User;
import com.example.eeza.backendbuhoseat.domain.entities.UserXLocalId;
import com.example.eeza.backendbuhoseat.exception.FavoriteAlreadyExistException;
import com.example.eeza.backendbuhoseat.exception.FavoriteNotFoundException;
import com.example.eeza.backendbuhoseat.exception.LocalNotFoundException;
import com.example.eeza.backendbuhoseat.exception.UserNotFoundException;
import com.example.eeza.backendbuhoseat.repository.FavoriteRepository;
import com.example.eeza.backendbuhoseat.repository.LocalRepository;
import com.example.eeza.backendbuhoseat.repository.UserRepository;
import com.example.eeza.backendbuhoseat.services.FavoriteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final LocalRepository localRepository;

    @Override
    @Transactional
    public void createFavorite(CreateFavoriteRequest request) {
        UUID userId = request.getUserId();
        UUID localId = request.getLocalId();
        UserXLocalId id = new UserXLocalId(userId, localId);

        if (favoriteRepository.existsById(id)) {
            throw new FavoriteAlreadyExistException(ENTITY_FAVORITE + EXISTS);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ENTITY_USER+NOT_FOUND));

        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new LocalNotFoundException(ENTITY_LOCAL+NOT_FOUND));

        Favorite favorite = Favorite.builder()
                .id(id)
                .user(user)
                .local(local)
                .build();

        favoriteRepository.save(favorite);
    }

    @Override
    public void getFavorite(FavoriteDto request) {
        UserXLocalId id = new UserXLocalId(request.getUserId(), request.getLocalId());

        favoriteRepository.findById(id)
                .orElseThrow(() -> new FavoriteNotFoundException(ENTITY_FAVORITE+NOT_FOUND));
    }

    @Override
    @Transactional
    public void deleteFavorite(FavoriteDto request) {
        UserXLocalId id = new UserXLocalId(request.getUserId(), request.getLocalId());

        if (!favoriteRepository.existsById(id)) {
            throw new FavoriteNotFoundException(ENTITY_FAVORITE + NOT_FOUND);
        }

        favoriteRepository.deleteById(id);
    }
}
