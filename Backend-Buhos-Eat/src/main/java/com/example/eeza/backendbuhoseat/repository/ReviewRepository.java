package com.example.eeza.backendbuhoseat.repository;

import com.example.eeza.backendbuhoseat.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByLocalId(UUID localId);
    Optional<Review> findByUser_IdAndLocal_Id(UUID userId, UUID localId);
}
