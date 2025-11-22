package com.example.eeza.backendbuhoseat.repository;

import com.example.eeza.backendbuhoseat.domain.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<Local, UUID> {
    Local findByName(String name);
    Local findLocalById(UUID id);
    Boolean existsLocalByDirection_LatAndDirection_Lng(Double directionLat, Double directionLng);
}
