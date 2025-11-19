package com.example.eeza.backendbuhoseat.repository;

import com.example.eeza.backendbuhoseat.domain.dto.response.plate.PlateResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlateRepository extends JpaRepository<Plate, UUID> {
    Plate findByName(String name);
    @Query("SELECT p FROM Plate p WHERE p.local.id = :idLocal")
    List<Plate> getPlatesByLocalId(@Param("idLocal") UUID idLocal);
}
