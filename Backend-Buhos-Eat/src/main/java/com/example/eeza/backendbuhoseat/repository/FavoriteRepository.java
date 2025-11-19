package com.example.eeza.backendbuhoseat.repository;

import com.example.eeza.backendbuhoseat.domain.entities.Favorite;
import com.example.eeza.backendbuhoseat.domain.entities.UserXLocalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UserXLocalId> {
}
