package com.example.eeza.backendbuhoseat.repository;

import com.example.eeza.backendbuhoseat.domain.entities.SubReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubReviewRepository extends JpaRepository<SubReview, UUID> {
    List<SubReview> findAllByReview_Id(UUID reviewId);
    List<SubReview> findAllByUser_Id(UUID userId);
    SubReview findByUser_IdAndReview_Id(UUID userId, UUID reviewId);
    Boolean existsByUser_IdAndReview_Id(UUID userId, UUID reviewId);
}
